package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.DiceGenerator
import com.maxpilotto.krand.generators.NaturalGenerator

object Pick {
    @JvmOverloads
    @JvmStatic
    fun <T> weighted(items: Array<T>, weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
        return weighted(items.toList(), weights, count, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> weighted(items: Map<T, Int>, count: Int, seed: Any? = null): List<T> {
        return weighted(items.keys, items.values, count, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> weighted(items: Iterable<T>, weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
        return List(count) {        //TODO: Improve the performances of this
            weighted(items, weights, seed)
        }
    }

    @JvmOverloads
    @JvmStatic
    inline fun <reified T> weighted(weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
        return List(count) {
            weighted(weights, seed)
        }
    }

    @JvmOverloads
    @JvmStatic
    fun <T> weighted(items: Array<T>, weights: Iterable<Int>, seed: Any? = null): T {
        return weighted(items.toList(), weights, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> weighted(items: Map<T, Int>, seed: Any? = null): T {
        return weighted(items.keys, items.values, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> weighted(items: Iterable<T>, weights: Iterable<Int>, seed: Any? = null): T {
        val sum = weights.filter { it > 0 }.sum()

        if (items.count() != weights.count()) {
            throw Exception("Items and weights must have the same size")
        }

        if (sum == 0) {
            throw Exception("The sum of all weights must be higher than 0")
        }

        val selected = NaturalGenerator(seed)
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

    @JvmOverloads
    @JvmStatic
    inline fun <reified T> weighted(weights: Iterable<Int>, seed: Any? = null): T {
        val values = T::class.java.enumConstants

        return weighted(values, weights, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> one(items: Iterable<T>, seed: Any? = null): T {
        val index = NaturalGenerator(seed)
            .max(items.count() - 1)
            .one()

        return items.elementAt(index)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> one(items: Array<T>, seed: Any? = null): T {
        return one(items.toList(), seed)
    }

    @JvmOverloads
    @JvmStatic
    fun one(string: String, seed: Any? = null): String {
        return one(string.toList(), seed).toString()
    }

    @JvmOverloads
    @JvmStatic
    inline fun <reified T> one(seed: Any? = null): T {
        val values = T::class.java.enumConstants

        return one(values, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> many(items: Iterable<T>, count: Int, seed: Any? = null): List<T> {
        val indexes = DiceGenerator(seed)
            .rolls(count)
            .max(items.count() - 1)
            .one()

        return List(count) {
            items.elementAt(indexes.elementAt(it))
        }
    }

    @JvmOverloads
    @JvmStatic
    fun <T> many(items: Array<T>, count: Int, seed: Any? = null): List<T> {
        return many(items.toList(), count, seed)
    }

    @JvmOverloads
    @JvmStatic
    fun many(string: String, count: Int, seed: Any? = null): String {
        return many(string.toList(), count, seed).joinToString("")
    }

    @JvmOverloads
    @JvmStatic
    inline fun <reified T> many(count: Int, seed: Any? = null): List<T> {
        val values = T::class.java.enumConstants

        return many(values, count, seed)
    }
}