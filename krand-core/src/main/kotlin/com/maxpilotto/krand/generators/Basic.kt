package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("bool", Boolean::class)
abstract class _BoolGenerator { //TODO: These classes or interfaces should be marked as internal
    val likelihood: Int = 50
}

//FIXME: The AbstractGenerator cannot and shouldn't generate nullable values but this generator may return null
//@Generator("falsy", Any::class)
//interface _FalsyGenerator

@Generator("floating", Double::class)
abstract class _DecimalGenerator {
    val min: Int = Int.MIN_VALUE
    val max: Int = Int.MAX_VALUE
    val fixed: Int = 4
}

@Generator("integer", Int::class)
abstract class _IntegerGenerator {
    val min: Int = Int.MIN_VALUE
    val max: Int = Int.MAX_VALUE
}

@Generator("prime", Int::class)
abstract class _PrimeGenerator {
    val min: Int = 0
    val max: Int = 10000
}

@Generator("natural", Int::class)
abstract class _NaturalGenerator {
    val min: Int = 0
    val max: Int = Int.MAX_VALUE
    val exclude: Array<Int> = arrayOf()
}
