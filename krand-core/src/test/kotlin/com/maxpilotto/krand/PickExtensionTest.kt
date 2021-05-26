package com.maxpilotto.krand

import com.maxpilotto.krand.extensions.pickOne
import com.maxpilotto.krand.extensions.pickWeighted
import com.maxpilotto.krand.extensions.pickWeightedSet
import org.junit.jupiter.api.Test

class PickExtensionTest {
    @Test
    fun pickSet() {
        val list = listOf("Apple", "Google", "Microsoft")
        val weights = listOf(0, 0, 100)

        assert(list.pickWeightedSet(weights, 10).all { it == "Microsoft" })
    }
}