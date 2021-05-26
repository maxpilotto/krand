package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("bool", Boolean::class)
interface _BoolGenerator {
    fun gen(likelihood: Int = 50)
}

@Generator("falsy", Any::class)
interface _FalsyGenerator

@Generator("floating", Float::class)
interface _FloatGenerator {
    fun gen(
        min: Int = 0,
        max: Int = 100,
        digits: Int = 8
    )
}

@Generator("integer", Int::class)
interface _IntegerGenerator {
    fun gen(
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE
    )
}

@Generator("prime", Int::class)
interface _PrimeGenerator {
    fun gen(
        min: Int = 0,
        max: Int = 100
    )
}

@Generator("natural", Int::class)
interface _NaturalGenerator {
    fun gen(
        min: Int = 0,
        max: Int = Int.MAX_VALUE,
        exclude: Array<Int> = arrayOf()
    )
}