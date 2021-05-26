package com.maxpilotto.krand

import com.maxpilotto.krand.generators.GenderGenerator
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun gender(){
        val g = GenderGenerator().gen(arrayOf(
            "Male_FTM", "Female_MFT", "Non-Binary"
        ))

        println(g)
    }
}