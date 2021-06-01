package com.maxpilotto.krand

import com.maxpilotto.krand.generators.GenderGenerator
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun gender() {
        val g = GenderGenerator().extraGenders(
            arrayOf(
                "Male (FTM)", "Female (MTF)", "Non Binary"
            )
        ).one()

        println(g)
    }
}