package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.Currency
import com.maxpilotto.krand.processor.annotations.Generator

@Generator("cc", String::class)
interface _CreditCardNumberGenerator {
    fun gen(
        type: String = ""   //TODO: Enum
    )
}

@Generator("cc_type", String::class)
interface _CreditCardTypeGenerator

@Generator("currency", Currency::class)
interface _CurrencyGenerator

@Generator("currency_pair", String::class)
interface _CurrencyPairGenerator

@Generator("dollar", String::class)
interface _DollarGenerator {
    fun gen(
        max: Float = 10000F
    )
}

@Generator("euro", String::class)
interface _EuroGenerator {
    fun gen(
        max: Float = 10000F
    )
}

@Generator("exp", String::class)
interface _CreditCardExpiryGenerator

@Generator("exp_month", String::class)
interface _CreditCardExpiryMonthGenerator {
    fun gen(
        future: Boolean = true
    )
}

@Generator("exp_year", String::class)
interface _CreditCardExpiryYearGenerator {
    fun gen(
        future: Boolean = true
    )
}