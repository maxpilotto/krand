package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.NaturalGenerator

class Picker(val seed: Any? = null) {
    private val rng = NaturalGenerator(seed)

    fun <T> weighted(items: Array<T>, weights: Iterable<Int>, count: Int): List<T> {    //FIXME: Should return an array since one is provided
        return weighted(items.toList(), weights, count)
    }

    fun <T> weighted(items: Map<T, Int>, count: Int): List<T> {
        return weighted(items.keys, items.values, count)
    }

    fun <T> weighted(items: Iterable<T>, weights: Iterable<Int>, count: Int): List<T> {
        return List(count) {
            weighted(items, weights)
        }
    }

    inline fun <reified T> weighted(weights: Iterable<Int>, count: Int): List<T> {
        return List(count) {
            weighted(weights)
        }
    }

    fun <T> weighted(items: Array<T>, weights: Iterable<Int>): T {
        return weighted(items.toList(), weights)
    }

    fun <T> weighted(items: Map<T, Int>): T {
        return weighted(items.keys, items.values)
    }

    fun <T> weighted(items: Iterable<T>, weights: Iterable<Int>): T {
        val sum = weights.filter { it > 0 }.sum()

        if (items.count() != weights.count()) {
            throw Exception("Items and weights must have the same size")
        }

        if (sum == 0) {
            throw Exception("The sum of all weights must be higher than 0")
        }

        val selected = rng
            .max(sum)
            .one()
        var total = 0

        for (i in 0 until weights.count()) {
            val weight = weights.elementAt(i)

            total += weight

            if (weight > 0) {
                if (selected <= total) {
                    return items.elementAt(i)
                }
            }
        }

        return items.last()
    }

    inline fun <reified T> weighted(weights: Iterable<Int>): T {
        val values = T::class.java.enumConstants

        return weighted(values, weights)
    }

    fun <T> one(items: Iterable<T>): T {
        val index = rng
            .max(items.count() - 1)
            .one()

        return items.elementAt(index)
    }

    fun <T> one(items: Array<T>): T {
        return one(items.toList())
    }

    fun one(string: String): String {
        return one(string.toList()).toString()
    }

    inline fun <reified T> one(): T {
        val values = T::class.java.enumConstants

        return one(values)
    }

    fun <T> many(items: Iterable<T>, count: Int): List<T> {
        return List(count) {
            one(items)
        }
    }

    fun <T> many(items: Array<T>, count: Int): List<T> {
        return List(count) {
            one(items)
        }
    }

    fun many(string: String, count: Int): String {
        return buildString {
            repeat(count) {
                append(one(string))
            }
        }
    }

    inline fun <reified T> many(count: Int): List<T> {
        return List(count) {
            one()
        }
    }
}