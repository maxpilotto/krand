package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.BaseGenerator

class DiceGenerator(seed: Any? = null) : BaseGenerator<Iterable<Int>>("rpg", seed) {
    fun many(
        rolls: Int,
        sum: Boolean? = null,
        count: Int
    ) = List(count) { one("${rolls}d6", sum) }

    fun many(
        rolls: Int,
        max: Int,
        sum: Boolean? = null,
        count: Int
    ) = List(count) { one("${rolls}d$max", sum) }

    fun many(
        dice: String = "1d6",
        sum: Boolean? = null,
        count: Int
    ) = List(count) { one(dice, sum) }

    fun string(
        rolls: Int
    ) = one(rolls).toString()

    fun string(
        rolls: Int,
        max: Int
    ) = one("${rolls}d$max").toString()

    fun string(
        dice: String = "1d6",
        sum: Boolean? = null
    ) = one(dice, sum).toString()

    fun one(
        rolls: Int
    ) = one("${rolls}d6")

    fun one(
        rolls: Int,
        max: Int
    ) = one("${rolls}d$max")

    fun one(
        dice: String = "1d6",
        sum: Boolean? = null
    ): Iterable<Int> {
        if (!dice.matches(Regex("\\d+d\\d+"))) {
            throw Exception("Input must match the format: #d#")
        }

        return one(
            arrayOf(
                dice
            ),
            mapOf(
                "sum" to sum
            )
        )
    }

    companion object {
        const val DEFAULT_DICE = "1d6"
    }
}