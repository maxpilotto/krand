package com.maxpilotto.krand

import com.maxpilotto.krand.generators.IntegerGenerator
import org.junit.jupiter.api.Test

class SeedTest {
    @Test
    fun integer() {
        val seed = "Seed Test"

        IntegerGenerator(seed).one().also {
            assert(IntegerGenerator(seed).one() == it)
        }
    }
}