package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.BaseGenerator

class EvenNumberGenerator(seed: Any? = null) : BaseGenerator<Int>("integer", seed) {
    fun gen(
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE
    ): Int {
        val n = gen(
            mapOf(
                "min" to min,
                "max" to max
            )
        )

        return if (n % 2 == 0) n else if (n - 1 >= min) n - 1 else n + 1
    }
}