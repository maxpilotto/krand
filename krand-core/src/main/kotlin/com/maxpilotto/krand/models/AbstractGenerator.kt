package com.maxpilotto.krand.models

import org.mozilla.javascript.Context
import org.mozilla.javascript.NativeArray
import org.mozilla.javascript.NativeObject
import java.util.*

abstract class AbstractGenerator<R>(
    val seed: Any? = null
) {
     protected val chance: Chance = Chance(seed)

    /**
     * Executes the given [functionName] with no arguments
     *
     * The resulting function call will be `Chance(seed).functionName()`
     */
    protected inline fun <reified R> execute(functionName: String): R {
        return execute(functionName, listOf(), mapOf())
    }

    /**
     * Executes the given [functionName] with the given [args]
     *
     * The resulting function call will be `Chance(seed).functionName(args)`
     */
    protected inline fun <reified R> execute(functionName: String, args: List<Any>): R {
        return execute(functionName, args, mapOf())
    }

    /**
     * Executes the given [functionName] with the given [options]
     *
     * The resulting function call will be `Chance(seed).functionName({ options })`
     */
    protected inline fun <reified R> execute(functionName: String, options: Map<String, Any?>): R {
        return execute(functionName, listOf(), options)
    }

    /**
     * Executes the given [functionName] with the given [args] and [options]
     *
     * The resulting function call will be `Chance(seed).functionName(args, { options })`
     */
    protected inline fun <reified R> execute(functionName: String, args: List<Any>, options: Map<String, Any?>): R {
        val value = chance.invoke(functionName, args, options)
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
                String::class -> value.toString()

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