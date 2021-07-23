package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.NaturalGenerator

class Shuffler(val seed: Any? = null) {
    private val rng = NaturalGenerator(seed)

    inline operator fun <reified T> invoke(array: Array<T>): Array<T> {
        return if (array.isEmpty()) {
            emptyArray()
        } else {
            invoke(array.toList()).run {
                Array(array.size) {
                    get(it)
                }
            }
        }
    }

    inline operator fun <reified T> invoke(vararg items: T): List<T> {
        return invoke(items.toList())
    }

    inline operator fun <reified E : Enum<E>> invoke(): List<E> {
        return invoke(E::class.java.enumConstants.toList())
    }

    operator fun invoke(string: String): String {
        return invoke(string.toList()).joinToString("")
    }

    operator fun <K, V> invoke(map: Map<K, V>): Map<K, V> {
        return invoke(map.toList()).toMap()
    }

    operator fun <T> invoke(iterable: Iterable<T>): List<T> {
        if (iterable.count() == 0) {
            return emptyList()
        }

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