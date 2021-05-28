package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.IntegerGenerator

class Pick {
    companion object {
        private val generator = IntegerGenerator()  //TODO: Maybe this is not needed

        @JvmOverloads
        fun <T> weighted(items: Array<T>, weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
            return weighted(items.toList(), weights, count, seed)
        }

        @JvmOverloads
        fun <T> weighted(items: Map<T, Int>, count: Int, seed: Any? = null): List<T> {
            return weighted(items.keys, items.values, count, seed)
        }

        @JvmOverloads
        fun <T> weighted(items: Iterable<T>, weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
            return List(count) {
                weighted(items, weights, seed)
            }
        }

        @JvmOverloads
        inline fun <reified T> weighted(weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
            return List(count) {
                weighted(weights, seed)
            }
        }

        @JvmOverloads
        fun <T> weighted(items: Array<T>, weights: Iterable<Int>, seed: Any? = null): T {
            return weighted(items.toList(), weights, seed)
        }

        @JvmOverloads
        fun <T> weighted(items: Map<T, Int>, seed: Any? = null): T {
            return weighted(items.keys, items.values, seed)
        }

        @JvmOverloads
        fun <T> weighted(items: Iterable<T>, weights: Iterable<Int>, seed: Any? = null): T {
            val sum = weights.filter { it > 0 }.sum()

            if (items.count() != weights.count()) {
                throw Exception("Items and weights must have the same size")
            }

            if (sum == 0) {
                throw Exception("The sum of all weights must be higher than 0")
            }

            val selected = (if (seed != null) IntegerGenerator(seed) else generator).one(0, sum)
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
        inline fun <reified T> weighted(weights: Iterable<Int>, seed: Any? = null): T {
            val values = T::class.java.enumConstants

            return weighted(values, weights, seed)
        }

        @JvmOverloads
        fun <T> one(iterable: Iterable<T>, seed: Any? = null): T {
            val generator = if (seed != null) IntegerGenerator(seed) else generator

            return iterable.elementAt(generator.one(0, iterable.count() - 1))
        }

        @JvmOverloads
        fun <T> one(items: Array<T>, seed: Any? = null): T {
            val generator = if (seed != null) IntegerGenerator(seed) else generator

            return items[generator.one(0, items.size - 1)]
        }

        @JvmOverloads
        inline fun <reified T> one(seed: Any? = null): T {
            val generator = IntegerGenerator(seed)
            val values = T::class.java.enumConstants

            return values[generator.one(0, values.size - 1)]
        }

        @JvmOverloads
        fun <T> many(iterable: Iterable<T>, count: Int, seed: Any? = null): List<T> {
            return List(count) {
                one(iterable, seed)
            }
        }

        @JvmOverloads
        fun <T> many(items: Array<T>, count: Int, seed: Any? = null): List<T> {
            return List(count) {
                one(items, seed)
            }
        }

        @JvmOverloads
        inline fun <reified T> many(count: Int, seed: Any? = null): List<T> {
            return List(count) {
                one(seed)
            }
        }
    }
}