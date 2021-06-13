package com.maxpilotto.krand

import com.maxpilotto.krand.utils.Shuffle
import org.junit.jupiter.api.Test

class ShuffleTest {
    enum class Brand {
        Microsoft,
        Apple,
        Google
    }

    private val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    private val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    private val string = "abc1234"
    private val seed = "ShuffleTestSeed"

    @Test
    fun array() {
        assert(Shuffle.array(array).distinct().size == array.size)
    }

    @Test
    fun list() {
        assert(Shuffle.list(list).distinct().size == list.size)
    }

    @Test
    fun items() {
        val gen = { Shuffle.items(1,2,3,4, seed = seed) }

        gen().apply {
            assert(gen() == this)
        }
    }

    @Test
    fun enum() {
        assert(Shuffle.enum<Brand>().distinct().size == Brand.values().size)
    }

    @Test
    fun string() {
        val result = Shuffle.string(string)

        assert(result != string)
        assert(result.toList().distinct().size == string.length)
    }
}