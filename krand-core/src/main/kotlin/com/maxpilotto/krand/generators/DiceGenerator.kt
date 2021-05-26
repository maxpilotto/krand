package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.BaseGenerator

class DiceGenerator(
    private val input: String,
    seed: Any? = null
) : BaseGenerator<Iterable<Int>>("rpg", seed) {
    constructor(rolls: Int, max: Int, seed: Any? = null) : this("${rolls}d$max", seed)
    constructor(rolls: Int, seed: Any? = null) : this(rolls, 6, seed)

    init {
        if (!input.matches(Regex("\\d+d\\d+"))) {
            throw Exception("Input must match the format: #d#")
        }
    }

    fun gen(
        sum: Boolean? = null
    ) = gen(
        arrayOf(
            input
        ),
        mapOf(
            "sum" to sum
        )
    )
}