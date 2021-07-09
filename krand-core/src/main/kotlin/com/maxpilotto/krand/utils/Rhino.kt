package com.maxpilotto.krand.utils

import org.mozilla.javascript.Context
import org.mozilla.javascript.ScriptableObject

internal object Rhino {
    private const val fileName = "index"
    val context: Context
    val globalScope: ScriptableObject

    init {
        val chanceJs = Resources.load("/chance.js").reader()

        context = Context.enter()
        globalScope = context.initSafeStandardObjects()
        context.optimizationLevel = -1
        context.evaluateString(globalScope, "exports = {}", fileName, 1, null)
        context.evaluateReader(globalScope, chanceJs, fileName, 1, null)
    }

    fun stringify(value: Any?): String {
        return when (value) {
            is String -> "\"$value\""
            is Iterable<*> -> value.joinToString(",", "[", "]", transform = { stringify(it) })
            is Array<*> -> value.joinToString(",", "[", "]", transform = { stringify(it) })

            else -> value.toString()
        }
    }

    fun evaluate(string: String): Any {
        return context.evaluateString(globalScope, string, fileName, 1, null)
    }

    fun createObject(props: Map<String, Any?>): Any {
        val source = props.filter { it.value != null }
            .map { it.key + ":" + stringify(it.value) }
            .joinToString(",", "x = {", "}")
        val script = context.compileString(source, fileName, 0, null)

        return script.exec(context, globalScope)
    }
}