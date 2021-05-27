package com.maxpilotto.krand.models

import com.maxpilotto.krand.utils.ChanceJSException
import com.maxpilotto.krand.utils.Resources
import org.mozilla.javascript.Context
import org.mozilla.javascript.NativeArray
import org.mozilla.javascript.NativeObject
import org.mozilla.javascript.ScriptableObject
import java.util.*

abstract class BaseGenerator<R>(
    val name: String,
    val seed: Any? = null
) {
    private val context: Context
    private val globalScope: ScriptableObject

    init {
        val chanceJs = Resources.load("/chance.js").reader()

        context = Context.enter()
        globalScope = context.initSafeStandardObjects()
        context.optimizationLevel = -1
        context.evaluateString(globalScope, "exports = {}", "index", 1, null)
        context.evaluateReader(globalScope, chanceJs, "index", 1, null)
    }

    protected fun javaify(value: Any?): Any? {
        return when {
            value is NativeArray -> value.toList()
            //FIXME: This should use the type rather than the generator name
            name == "birthday" || name == "date" -> Context.jsToJava(value, Date::class.java)
            name == "timezone" -> {
                value as NativeObject

                Timezone(
                    value["name"] as String,
                    value["abbr"] as String,
                    value["offset"] as Double,
                    value["isdst"] as Boolean,
                    value["text"] as String,
                    value["utc"] as List<String>,
                )
            }
            name == "currency" -> {
                value as NativeObject

                Currency(value["code"] as String, value["name"] as String)
            }

            else -> Context.jsToJava(value, Any::class.java)
        }
    }

    protected fun stringify(value: Any?): String {
        return when (value) {
            is String -> "\"$value\""
            is Iterable<*> -> value.joinToString(",", "[", "]", transform = { stringify(it) })
            is Array<*> -> value.joinToString(",", "[", "]", transform = { stringify(it) })

            else -> value.toString()
        }
    }

    //TODO: Replace all Arrays with Lists ?
    protected fun gen(name: String, args: Array<Any>, optionalArgs: Map<String, Any?>): R {
        val args = args.joinToString(",", transform = { stringify(it) })
        val optionalArgs = optionalArgs.filter { it.value != null }
            .map { "${it.key}: ${stringify(it.value)}" }
            .joinToString(",", "{", "}")
        var function = "exports.Chance"

        function += if (seed != null) "(\"$seed\")" else "()"
        function += if (args.isNotEmpty()) ".$name($args," else ".$name("
        function += if (optionalArgs.isNotEmpty()) "$optionalArgs)" else ")"    //FIXME: Empty optionalArgs is always {}

        println(function)

        val value = context.evaluateString(globalScope, function, "index", 1, null)
        val javaValue = javaify(value)

        return try {
            javaValue as R
        } catch (e: Exception) {
            throw ChanceJSException(e.message)
        }
    }

    fun gen(args: Array<Any>, optionalArgs: Map<String, Any?>): R {
        return gen(name, args, optionalArgs)
    }

    fun gen(optionalArgs: Map<String, Any?>): R {
        return gen(name, arrayOf(), optionalArgs)
    }

    //TODO: Methods for single and many items
}