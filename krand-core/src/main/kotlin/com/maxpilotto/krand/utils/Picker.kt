package com.maxpilotto.krand.utils

import kotlin.random.Random

class Picker(val seed: Any? = null) {
    private val rng = Random(seed.hashCode())

    fun <T> weighted(items: Array<T>, weights: Iterable<Number>, count: Int): List<T> {
        return weighted(items.toList(), weights, count)
    }

    fun <T> weighted(items: Map<T, Number>, count: Int): List<T> {
        return weighted(items.keys, items.values, count)
    }

    fun <T> weighted(items: Iterable<T>, weights: Iterable<Number>, count: Int): List<T> {
        return if (items.count() == 0) {
            emptyList()
        } else {
            List(count) {
                weighted(items, weights)
            }
        }
    }

    fun <K, V> weighted(map: Map<K, V>, weights: Iterable<Number>, count: Int): List<Map.Entry<K, V>> {
        return if (map.isEmpty()) {
            emptyList()
        } else {
            List(count) {
                weighted(map.entries, weights)
            }
        }
    }

    inline fun <reified T> weighted(weights: Iterable<Number>, count: Int): List<T> {
        return if (T::class.java.enumConstants.isEmpty()) {
            emptyList()
        } else {
            List(count) {
                weighted(weights)
            }
        }
    }

    fun <T> weighted(items: Array<T>, weights: Iterable<Number>): T {
        return weighted(items.toList(), weights)
    }

    fun <T> weighted(items: Map<T, Number>): T {
        return weighted(items.keys, items.values)
    }

    fun <T> weighted(items: Iterable<T>, weights: Iterable<Number>): T {
        val w = weights.map { it.toDouble() }
        val sum = w.filter { it > 0 }.sum()

        requireNotEmpty(items)
        require(items.count() == w.count()) { "Items and weights must have the same size" }
        require(sum > 0.0) { "The weights sum must be higher than 0" }

        val selected = rng.nextDouble(sum)
        var total = 0.0

        for (i in 0 until w.count()) {
            val weight = w.elementAt(i)

            total += weight

            if (weight > 0) {
                if (selected <= total) {
                    return items.elementAt(i)
                }
            }
        }

        return items.last()
    }

    fun <K, V> weighted(map: Map<K, V>, weights: Iterable<Number>): Map.Entry<K, V> {
        return weighted(map.entries, weights)
    }

    inline fun <reified T> weighted(weights: Iterable<Number>): T {
        val values = T::class.java.enumConstants

        return weighted(values, weights)
    }

    fun <T> many(items: Iterable<T>, count: Int): List<T> {
        return if (items.count() == 0) {
            emptyList()
        } else {
            List(count) {
                one(items)
            }
        }
    }

    fun <T> many(items: Array<T>, count: Int): List<T> {
        return if (items.isEmpty()) {
            emptyList()
        } else {
            List(count) {
                one(items)
            }
        }
    }

    fun many(string: String, count: Int): String {
        return if (string.isEmpty()) {
            ""
        } else {
            buildString {
                repeat(count) {
                    append(one(string))
                }
            }
        }
    }

    fun <K, V> many(map: Map<K, V>, count: Int): List<Map.Entry<K, V>> {
        return if (map.isEmpty()) {
            emptyList()
        } else {
            List(count) {
                one(map)
            }
        }
    }

    inline fun <reified T> many(count: Int): List<T> {
        return if (T::class.java.enumConstants.isEmpty()) {
            emptyList()
        } else {
            List(count) {
                one()
            }
        }
    }

    fun <T> one(items: Iterable<T>): T {
        requireNotEmpty(items)

        val index = rng.nextInt(items.count())

        return items.elementAt(index)
    }

    fun <T> one(items: Array<T>): T {
        return one(items.toList())
    }

    fun one(string: String): String {
        return one(string.toList()).toString()
    }

    fun <K, V> one(map: Map<K, V>): Map.Entry<K, V> {
        return one(map.entries)
    }

    inline fun <reified T> one(): T {
        val values = T::class.java.enumConstants

        return one(values)
    }

    private fun <T> requireNotEmpty(items: Iterable<T>) {
        require(items.count() > 0) { "No items provided" }
    }
}