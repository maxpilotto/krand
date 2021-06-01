package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.AbstractGenerator

class OddNumberGenerator(seed: Any? = null) : AbstractGenerator<Int>(seed) {
    var min: Int? = null
        private set
    var max: Int? = null
        private set

    override fun one(): Int {
        val n = execute<Int>(
            "integer", mapOf(
                "min" to min,
                "max" to max
            )
        )

        return if (n % 2 != 0) n else if (n - 1 >= min ?: Int.MIN_VALUE) n - 1 else n + 1
    }

    fun max(max: Int) = apply {
        this.max = max
    }

    fun min(min: Int) = apply {
        this.min = min
    }
}