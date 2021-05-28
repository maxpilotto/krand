package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.BaseGenerator

class OddNumberGenerator(seed: Any? = null) : BaseGenerator<Int>("integer", seed) {
    fun many(
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE,
        count: Int
    ) = List(count) { one(min, max) }

    fun string(
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE
    ) = one(min, max).toString()

    fun one(
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE
    ): Int {
        val n = one(
            mapOf(
                "min" to min,
                "max" to max
            )
        )

        return if (n % 2 != 0) n else if (n - 1 >= min) n - 1 else n + 1
    }
}