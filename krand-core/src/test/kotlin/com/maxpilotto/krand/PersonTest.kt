package com.maxpilotto.krand

import com.maxpilotto.krand.generators.AadharGenerator
import com.maxpilotto.krand.generators.GenderGenerator
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun gender() {
        val g = GenderGenerator().extraGenders(
            "Male (FTM)",
            "Female (MTF)",
            "Non Binary"
        ).one()

        println(g)
    }

    @Test
    fun aadhar() {
        assert(AadharGenerator().separatedByWhiteSpace(false).onlyLastFour(false).one().length == 12)
    }
}