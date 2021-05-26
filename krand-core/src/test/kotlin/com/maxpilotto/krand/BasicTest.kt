package com.maxpilotto.krand

import com.maxpilotto.krand.generators.BoolGenerator
import com.maxpilotto.krand.generators.FloatGenerator
import com.maxpilotto.krand.generators.IntegerGenerator
import com.maxpilotto.krand.generators.NaturalGenerator
import com.maxpilotto.krand.generators.EvenNumberGenerator
import com.maxpilotto.krand.generators.OddNumberGenerator
import org.junit.jupiter.api.Test

class BasicTest {
    @Test
    fun even() {
        val range = 0 until 30

        for (i in range) {
            val n = EvenNumberGenerator().gen(0, 10)

            assert(n in range && n % 2 == 0)
        }
    }

    @Test
    fun odd() {
        val range = 0 until 30

        for (i in range) {
            val n = OddNumberGenerator().gen(0, 10)

            assert(n in range && n % 2 != 0)
        }
    }

    @Test
    fun bool() {
        assert(BoolGenerator().gen(likelihood = 100))
    }

    @Test
    fun float() {
        assert(FloatGenerator().gen(0, 100) in 0F..100F)
    }

    @Test
    fun integer() {
        assert(IntegerGenerator().gen(0, 100) in 0..100)
    }

    @Test
    fun natural() {
        assert(NaturalGenerator().gen(0, 1, arrayOf(1)) == 0)
    }
}