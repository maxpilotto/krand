package com.maxpilotto.krand

import com.maxpilotto.krand.generators.*
import org.junit.jupiter.api.Test

class BasicTest {
    @Test
    fun even() {
        val range = 0 until 30

        for (i in range) {
            val gen = EvenNumberGenerator()
                .min(0)
                .max(10)
            val n = gen.one()

            assert(n in range && n % 2 == 0)
            assert(gen.many(count = 10).all { it % 2 == 0 })
        }
    }

    @Test
    fun odd() {
        val range = 0 until 30

        for (i in range) {
            val gen = OddNumberGenerator()
                .min(0)
                .max(10)
            val n = gen.one()

            assert(n in range && n % 2 != 0)
            assert(gen.many(count = 10).all { it % 2 != 0 })
        }
    }

    @Test
    fun bool() {
        assert(BoolGenerator().likelihood(100).one())
        assert(BoolGenerator().likelihood(100).many(10).all { it })
    }

    @Test
    fun float() {
        assert(DecimalGenerator().min(0).max(100).one() in 0F..100F)
        assert(DecimalGenerator().min(0).max(100).many(10).all { it in 0F..100F })
    }

    @Test
    fun integer() {
        assert(IntegerGenerator().min(0).max(100).one() in 0..100)
        assert(IntegerGenerator().min(0).max(100).many(10).all { it in 0..100 })
    }

    @Test
    fun natural() {
        assert(NaturalGenerator().min(0).max(1).exclude(arrayOf(1)).one() == 0)
        assert(NaturalGenerator().min(0).max(1).exclude(arrayOf(1)).many(10).all { it == 0 })
    }
}