package com.maxpilotto.krand

import com.maxpilotto.krand.generators.DiceGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertTrue

class DiceTest {
    @Test
    fun diceTest() {
        assertAll(
            { assert(DiceGenerator("10d6").gen().count() == 10) },
            { assert(DiceGenerator("12d6").gen().count() == 12) },
            { assert(DiceGenerator("20d6").gen().count() == 20) }
        )

        assertTrue {
            val rolls = DiceGenerator("5d6").gen()

            rolls.count() == 5 && rolls.all { it <= 6 }
        }
    }
}