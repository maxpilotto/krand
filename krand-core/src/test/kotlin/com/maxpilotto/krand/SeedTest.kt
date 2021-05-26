package com.maxpilotto.krand

import com.maxpilotto.krand.generators.IntegerGenerator
import com.maxpilotto.krand.utils.Pick
import org.junit.jupiter.api.Test

class SeedTest {
    @Test
    fun integer() {
        val seed = "Seed Test"

        IntegerGenerator(seed).gen().also {
            assert(IntegerGenerator(seed).gen() == it)
        }
    }

    @Test
    fun pickOne() {
        val items = arrayOf(4, 8, 15, 16, 23, 42)
        val seed = "Seed Test"

        Pick.one(items, seed).also {
            assert(Pick.one(items, seed) == it)
        }
    }

    @Test
    fun pickSet() {
        val items = arrayOf(4, 8, 15, 16, 23, 42)
        val seed = "Seed Test"

        Pick.set(items, 3, seed).also {
            assert(Pick.set(items, 3, seed) == it)
        }
    }

    @Test
    fun pickWeighted() {
        val list = listOf("Apple", "Google", "Microsoft")
        val weights = listOf(10, 50, 50)
        val seed = "Seed Test Weighted"

        Pick.weighted(list, weights, seed).also {
            assert(Pick.weighted(list, weights, seed) == it)
        }
    }

    @Test
    fun pickWeightedSet() {
        val list = listOf("Apple", "Google", "Microsoft")
        val weights = listOf(10, 50, 50)
        val seed = "Seed Test Weighted"
        val pick = { Pick.weightedSet(list, weights, 5, seed) }

        pick().also {
            assert(pick() == it)
        }
    }
}