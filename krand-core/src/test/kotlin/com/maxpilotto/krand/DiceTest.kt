package com.maxpilotto.krand

import com.maxpilotto.krand.generators.DiceGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertTrue

class DiceTest {
    @Test
    fun dice() {
        assertAll(
            { assert(DiceGenerator().one("10d6").count() == 10) },
            { assert(DiceGenerator().one("12d6").count() == 12) },
            { assert(DiceGenerator().one("20d6").count() == 20) }
        )

        assertTrue {
            val rolls = DiceGenerator().one("5d6")

            rolls.count() == 5 && rolls.all { it <= 6 }
        }
    }

    @Test
    fun diceMany() {
        println(
            DiceGenerator().many(2, 6, false, 10).flatten().size == 20
        )
    }
}