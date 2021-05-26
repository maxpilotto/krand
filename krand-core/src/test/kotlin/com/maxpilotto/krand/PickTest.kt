package com.maxpilotto.krand

import com.maxpilotto.krand.utils.Pick
import org.junit.jupiter.api.Test

class PickTest {
    @Test
    fun pickWeighted() {
        val list = listOf("Apple", "Google")
        val weights = listOf(0, 100)

        assert(
            Pick.weighted(list, weights) == "Google"
        )
    }
}