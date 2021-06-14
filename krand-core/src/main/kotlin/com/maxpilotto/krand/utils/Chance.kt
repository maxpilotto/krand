package com.maxpilotto.krand.utils

import org.mozilla.javascript.Context
import org.mozilla.javascript.ScriptableObject

object Chance {
    private const val fileName = "index"
    private val context: Context
    private val globalScope: ScriptableObject

    init {
        val chanceJs = Resources.load("/chance.js").reader()

        context = Context.enter()
        globalScope = context.initSafeStandardObjects()
        context.optimizationLevel = -1
        context.evaluateString(globalScope, "exports = {}", fileName, 1, null)
        context.evaluateReader(globalScope, chanceJs, fileName, 1, null)
    }

    fun evaluateString(string: String): Any {
        return context.evaluateString(globalScope, string, fileName, 1, null)
    }
}