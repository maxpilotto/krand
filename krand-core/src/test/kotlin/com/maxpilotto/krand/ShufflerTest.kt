package com.maxpilotto.krand

import com.maxpilotto.krand.utils.Shuffler
import org.junit.jupiter.api.Test

class ShufflerTest {
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
        assert(Shuffler()(array).distinct().size == array.size)
    }

    @Test
    fun list() {
        assert(Shuffler()(list).distinct().size == list.size)
    }

    @Test
    fun items() {
        val gen = { Shuffler(seed)(1, 2, 3, 4) }

        gen().apply {
            assert(gen() == this)
        }
    }

    @Test
    fun enum() {
        assert(Shuffler()<Brand>().distinct().size == Brand.values().size)
    }

    @Test
    fun string() {
        val result = Shuffler()(string)

        assert(result != string)
        assert(result.toList().distinct().size == string.length)
        assert(Shuffler()(string) != Shuffler()(string))
    }

    @Test
    fun stringSeed() {
        assert(
            Shuffler(seed)(string) == Shuffler(seed)(string)
        )
    }
}