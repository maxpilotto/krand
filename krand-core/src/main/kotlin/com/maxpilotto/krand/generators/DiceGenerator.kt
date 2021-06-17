package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.AbstractGenerator

class DiceGenerator(seed: Any? = null) : AbstractGenerator<Iterable<Int>>(seed) {
    var rolls: Int? = 2
        private set
    var max: Int? = 6
        private set

    override fun one() = execute<Iterable<Double>>(
        "rpg",
        listOf(
            "${rolls}d$max"
        ),
        mapOf()
    ).map { it.toInt() }

    fun dice(dice: String) = apply {
        require(dice.matches(Regex("^\\d+d\\d+$"))) { "Dice must match format: #d#" }

        dice.split("d").also {
            rolls(it[0].toInt())
            max(it[1].toInt())
        }
    }

    fun rolls(rolls: Int) = apply {
        this.rolls = rolls
    }

    fun max(max: Int) = apply {
        this.max = max
    }
}