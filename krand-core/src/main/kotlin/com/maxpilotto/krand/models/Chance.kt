package com.maxpilotto.krand.models

import com.maxpilotto.krand.utils.Rhino
import org.mozilla.javascript.Function
import org.mozilla.javascript.NativeObject

class Chance(val seed: Any? = null) {
    private val nativeObject: NativeObject

    init {
        val generator = if (seed != null) "Chance(${Rhino.stringify(seed)})" else "Chance()"
        val obj = Rhino.evaluate("new exports.$generator")

        if (obj is NativeObject) {
            nativeObject = obj
        } else {
            throw Exception("Cannot instantiate ChanceJS")
        }
    }

    operator fun invoke(name: String, args: List<Any?>, options: Map<String, Any?>): Any {
        if (nativeObject.prototype.has(name, nativeObject)) {
            val function = nativeObject.prototype[name, nativeObject]

            if (function is Function) {
                val parameters = mutableListOf<Any?>()

                parameters.addAll(args)
                parameters.add(Rhino.createObject(options))

                return function.call(
                    Rhino.context,
                    Rhino.globalScope,
                    nativeObject,
                    parameters.toTypedArray()
                )
            }
        }

        throw Exception("Function $name was not found")
    }
}