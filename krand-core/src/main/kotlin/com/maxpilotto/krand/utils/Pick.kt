package com.maxpilotto.krand.utils

import com.maxpilotto.krand.generators.IntegerGenerator

class Pick {
    companion object {
        private val generator = IntegerGenerator()  //TODO: Maybe this is not needed

        @JvmOverloads
        fun <T> weightedSet(items: Array<T>, weights: Iterable<Int>, count: Int, seed: Any? = null): Set<T> {
            return weightedSet(items.toList(), weights, count, seed)
        }

        @JvmOverloads
        fun <T> weightedSet(items: Map<T, Int>, count: Int, seed: Any? = null): Set<T> {
            return weightedSet(items.keys, items.values, count, seed)
        }

        @JvmOverloads
        fun <T> weightedSet(items: Iterable<T>, weights: Iterable<Int>, count: Int, seed: Any? = null): Set<T> {
            return mutableSetOf<T>().apply {
                for (i in 0 until count) {
                    add(weighted(items, weights, seed))
                }
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

            val selected = (if (seed != null) IntegerGenerator(seed) else generator).gen(0, sum)
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
        fun <T> one(iterable: Iterable<T>, seed: Any? = null): T {
            val generator = if (seed != null) IntegerGenerator(seed) else generator

            return iterable.elementAt(generator.gen(0, iterable.count() - 1))
        }

        @JvmOverloads
        fun <T> one(items: Array<T>, seed: Any? = null): T {
            val generator = if (seed != null) IntegerGenerator(seed) else generator

            return items[generator.gen(0, items.size - 1)]
        }

        @JvmOverloads
        inline fun <reified T> one(seed: Any? = null): T {
            val generator = IntegerGenerator(seed)
            val values = T::class.java.enumConstants

            return values[generator.gen(0, values.size - 1)]
        }

        @JvmOverloads
        fun <T> set(iterable: Iterable<T>, count: Int, seed: Any? = null): Set<T> {
            return mutableSetOf<T>().apply {
                for (i in 0 until count) {
                    add(one(iterable, seed))
                }
            }
        }

        @JvmOverloads
        fun <T> set(items: Array<T>, count: Int, seed: Any? = null): Set<T> {
            return mutableSetOf<T>().apply {
                for (i in 0 until count) {
                    add(one(items, seed))
                }
            }
        }

        @JvmOverloads
        inline fun <reified T> set(count: Int, seed: Any? = null): Set<T> {
            return mutableSetOf<T>().apply {
                for (i in 0 until count) {
                    add(one(seed))
                }
            }
        }
    }
}