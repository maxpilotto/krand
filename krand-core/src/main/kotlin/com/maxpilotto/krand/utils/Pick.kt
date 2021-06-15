package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.IntegerGenerator

object Pick {
    private val gen: (Any?, Int, Int) -> Int = { seed, min, max ->
        IntegerGenerator(seed)
            .min(min)
            .max(max)
            .one()
    }

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
        return List(count) {
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

        val selected = gen(seed, 0, sum)
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
    fun <T> one(iterable: Iterable<T>, seed: Any? = null): T {
        return iterable.elementAt(gen(seed, 0, iterable.count() - 1))
    }

    @JvmOverloads
    @JvmStatic
    fun <T> one(items: Array<T>, seed: Any? = null): T {
        return items[gen(seed, 0, items.size - 1)]
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
        val gen = IntegerGenerator(seed)
            .min(0)
            .max(values.size - 1)

        return values[gen.one()]
    }

    @JvmOverloads
    @JvmStatic
    fun <T> many(iterable: Iterable<T>, count: Int, seed: Any? = null): List<T> {
        return List(count) {
            one(iterable, seed)
        }
    }

    @JvmOverloads
    @JvmStatic
    fun <T> many(items: Array<T>, count: Int, seed: Any? = null): List<T> {
        return List(count) {
            one(items, seed)
        }
    }

    @JvmOverloads
    @JvmStatic
    fun many(string: String, count: Int, seed: Any? = null): String {
        return many(string.toList(), count, seed).joinToString("")
    }

    @JvmOverloads
    @JvmStatic
    inline fun <reified T> many(count: Int, seed: Any? = null): List<T> {
        return List(count) {
            one(seed)
        }
    }

}