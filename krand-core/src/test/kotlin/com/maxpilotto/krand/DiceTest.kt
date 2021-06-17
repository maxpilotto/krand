package com.maxpilotto.krand

import com.maxpilotto.krand.generators.DiceGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertTrue

class DiceTest {
    @Test
    fun dice() {
        assertAll(
            { assert(DiceGenerator().dice("10d6").one().count() == 10) },
            { assert(DiceGenerator().dice("12d6").one().count() == 12) },
            { assert(DiceGenerator().dice("20d6").one().count() == 20) }
        )

        assertTrue {
            val rolls = DiceGenerator().dice("5d6").one()

            rolls.count() == 5 && rolls.all { it <= 6 }
        }
    }

    @Test
    fun diceMany() {
        assert(
            DiceGenerator().rolls(2).max(6).many(10).flatten().size == 20
        )
    }
}