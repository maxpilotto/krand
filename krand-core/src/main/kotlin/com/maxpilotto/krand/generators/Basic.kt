package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("bool", Boolean::class)
interface _BoolGenerator {
    val likelihood: Int
}

//FIXME: The AbstractGenerator cannot and shouldn't generate nullable values but this generator may return null
//@Generator("falsy", Any::class)
//interface _FalsyGenerator

@Generator("floating", Double::class)
interface _DecimalGenerator {
    val min: Int
    val max: Int
    val fixed: Int
}

@Generator("integer", Int::class)
interface _IntegerGenerator {
    val min: Int
    val max: Int
}

@Generator("prime", Int::class)
interface _PrimeGenerator {
    val min: Int
    val max: Int
}

@Generator("natural", Int::class)
interface _NaturalGenerator {
    val min: Int
    val max: Int
    val exclude: Array<Int>
}
