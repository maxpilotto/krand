package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.NaturalGenerator

object Shuffle {
    @JvmOverloads
    @JvmStatic
    inline fun <reified T> array(array: Array<T>, seed: Any? = null): Array<T> {
        return list(array.toList(), seed).toTypedArray()
    }

    @JvmOverloads
    @JvmStatic
    inline fun <reified T> items(vararg items: T, seed: Any? = null): Iterable<T> {
        return list(items.toList(), seed)
    }

    @JvmOverloads
    @JvmStatic
    inline fun <reified E : Enum<E>> enum(seed: Any? = null): Iterable<E> {
        return list(E::class.java.enumConstants.toList(), seed)
    }

    @JvmOverloads
    @JvmStatic
    fun string(string: String, seed: Any? = null): String {
        return list(string.toList(), seed).joinToString("")
    }

    @JvmOverloads
    @JvmStatic
    fun <T> list(iterable: Iterable<T>, seed: Any? = null): List<T> {
        val rng = NaturalGenerator(seed)
        val out = mutableListOf<T>()
        val indexes = MutableList(iterable.count()) { it }
        var last = iterable.count() - 1
        var selected = 0

        for (i in 0 until iterable.count()) {
            selected = rng.max(last).one()

            out.add(iterable.elementAt(indexes[selected]))
            indexes[selected] = indexes[last]
            last--
        }

        return out
    }
}