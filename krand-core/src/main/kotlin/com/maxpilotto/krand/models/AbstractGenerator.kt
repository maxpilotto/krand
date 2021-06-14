package com.maxpilotto.krand.models

import com.maxpilotto.krand.utils.Chance
import com.maxpilotto.krand.utils.Resources
import org.mozilla.javascript.Context
import org.mozilla.javascript.NativeArray
import org.mozilla.javascript.NativeObject
import org.mozilla.javascript.ScriptableObject
import java.util.*

abstract class AbstractGenerator<R>(
    val seed: Any? = null
) {
    protected fun stringify(value: Any?): String {
        return when (value) {
            is String -> "\"$value\""
            is Iterable<*> -> value.joinToString(",", "[", "]", transform = { stringify(it) })
            is Array<*> -> value.joinToString(",", "[", "]", transform = { stringify(it) })

            else -> value.toString()
        }
    }

    /**
     * Executes the given [functionName] with the given [functionOptionalArgs]
     *
     * The resulting function call will be `Chance(seed).functionName({ functionOptionalArgs })`
     */
    protected inline fun <reified R> execute(functionName: String): R {
        return execute(functionName, listOf(), mapOf())
    }

    /**
     * Executes the given [functionName] with the given [functionOptionalArgs]
     *
     * The resulting function call will be `Chance(seed).functionName({ functionOptionalArgs })`
     */
    protected inline fun <reified R> execute(functionName: String, functionOptionalArgs: Map<String, Any?>): R {
        return execute(functionName, listOf(), functionOptionalArgs)
    }

    /**
     * Executes the given [functionName] with the given [functionArgs] and [functionOptionalArgs]
     *
     * The resulting function call will be `Chance(seed).functionName(functionArgs, { functionOptionalArgs })`
     */
    protected inline fun <reified R> execute(functionName: String, functionArgs: List<Any>, functionOptionalArgs: Map<String, Any?>): R {
        val args = functionArgs.joinToString(",", transform = { stringify(it) })
        val optionalArgs = functionOptionalArgs.filter { it.value != null }
            .map { "${it.key}: ${stringify(it.value)}" }
            .joinToString(",", "{", "}")
        var function = "exports.Chance"

        function += if (seed != null) "(\"$seed\")" else "()"
        function += if (args.isNotEmpty()) ".$functionName($args," else ".$functionName("
        function += "$optionalArgs)"

        val value = Chance.evaluateString(function)
        val valueType = value::class.java.canonicalName

        return when {
            valueType == "org.mozilla.javascript.NativeDate" -> Context.jsToJava(value, Date::class.java)
            value is NativeArray -> value.toList()
            value is NativeObject -> when (functionName) {
                "timezone" -> {
                    Timezone(
                        value["name"] as String,
                        value["abbr"] as String,
                        value["offset"] as Double,
                        value["isdst"] as Boolean,
                        value["text"] as String,
                        value["utc"] as List<String>,
                    )
                }
                "currency" -> {
                    Currency(
                        value["code"] as String,
                        value["name"] as String
                    )
                }

                else -> Context.jsToJava(value, Any::class.java)
            }
            value is Number -> when (R::class) {
                Double::class -> value.toDouble()
                Float::class -> value.toFloat()
                Long::class -> value.toLong()
                Int::class -> value.toInt()
                Char::class -> value.toChar()
                Short::class -> value.toShort()
                Byte::class -> value.toByte()

                else -> value
            }

            else -> Context.jsToJava(value, Any::class.java)
        } as R
    }

    /**
     * Generates one single item
     */
    abstract fun one(): R

    /**
     * Generates a single item as a String
     */
    fun string() = one().toString()

    /**
     * Generates the specified amount of items
     */
    fun many(count: Int) = List(count) { one() }
}