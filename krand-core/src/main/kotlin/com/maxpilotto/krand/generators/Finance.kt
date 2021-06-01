package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.Currency
import com.maxpilotto.krand.processor.annotations.Generator

@Generator("cc", String::class)
interface _CreditCardNumberGenerator {
    val type: String   //TODO: Enum
}

@Generator("cc_type", String::class)
interface _CreditCardTypeGenerator

@Generator("currency", Currency::class)
interface _CurrencyGenerator

@Generator("currency_pair", String::class)
interface _CurrencyPairGenerator

@Generator("dollar", String::class)
interface _DollarGenerator {
    val max: Float
}

@Generator("euro", String::class)
interface _EuroGenerator {
    val max: Float
}

@Generator("exp", String::class)
interface _CreditCardExpiryGenerator

@Generator("exp_month", String::class)
interface _CreditCardExpiryMonthGenerator {
    val future: Boolean
}

@Generator("exp_year", String::class)
interface _CreditCardExpiryYearGenerator {
    val future: Boolean
}