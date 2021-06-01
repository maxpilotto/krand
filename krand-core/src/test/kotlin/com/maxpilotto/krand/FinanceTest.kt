package com.maxpilotto.krand

import com.maxpilotto.krand.generators.CreditCardExpiryYearGenerator
import com.maxpilotto.krand.generators.CreditCardNumberGenerator
import com.maxpilotto.krand.generators.EuroGenerator
import org.junit.jupiter.api.Test
import java.util.*

class FinanceTest {
    @Test
    fun cc() {
        assert(CreditCardNumberGenerator().type("Mastercard").one().length == 16)
    }

    @Test
    fun euro() {
        assert(EuroGenerator().max(10F).one().matches(Regex("\\d{1,2}.\\d{0,2}€")))
    }

    @Test
    fun expiryYear() {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val range = year..year + 10

        assert(CreditCardExpiryYearGenerator().one().toInt() in range)
    }
}