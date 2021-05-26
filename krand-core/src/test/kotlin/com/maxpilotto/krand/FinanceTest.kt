package com.maxpilotto.krand

import com.maxpilotto.krand.generators.CreditCardExpiryYearGenerator
import com.maxpilotto.krand.generators.CreditCardNumberGenerator
import com.maxpilotto.krand.generators.EuroGenerator
import org.junit.jupiter.api.Test
import java.util.*

class FinanceTest {
    @Test
    fun cc() {
        assert(CreditCardNumberGenerator().gen("Mastercard").length == 16)
    }

    @Test
    fun euro() {
        assert(EuroGenerator().gen(10F).matches(Regex("\\d{1,2}.\\d{0,2}€")))
    }

    @Test
    fun expiryYear() {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val range = year..year+10

        assert(CreditCardExpiryYearGenerator().gen().toInt() in range)
    }
}