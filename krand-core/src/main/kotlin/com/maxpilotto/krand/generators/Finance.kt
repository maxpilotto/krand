package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.Currency
import com.maxpilotto.krand.processor.annotations.Generator

@Generator("cc", String::class)
internal interface _CreditCardNumberGenerator {
    val type: String
}

@Generator("cc_type", String::class)
internal interface _CreditCardTypeGenerator

@Generator("currency", Currency::class)
internal interface _CurrencyGenerator

@Generator("currency_pair", String::class)
internal interface _CurrencyPairGenerator

@Generator("dollar", String::class)
internal interface _DollarGenerator {
    val max: Float
}

@Generator("euro", String::class)
internal interface _EuroGenerator {
    val max: Float
}

@Generator("exp", String::class)
internal interface _CreditCardExpiryGenerator

@Generator("exp_month", String::class)
internal interface _CreditCardExpiryMonthGenerator {
    val future: Boolean
}

@Generator("exp_year", String::class)
internal interface _CreditCardExpiryYearGenerator {
    val future: Boolean
}