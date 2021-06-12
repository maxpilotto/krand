package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("bool", Boolean::class)
internal abstract class _BoolGenerator {
    val likelihood: Int = 50
}

//FIXME: The AbstractGenerator cannot and shouldn't generate nullable values but this generator may return null
//@Generator("falsy", Any::class)
//internal interface _FalsyGenerator

@Generator("floating", Double::class)
internal abstract class _DecimalGenerator {
    val min: Int = Int.MIN_VALUE
    val max: Int = Int.MAX_VALUE
    val fixed: Int = 4
}

@Generator("integer", Int::class)
internal abstract class _IntegerGenerator {
    val min: Int = Int.MIN_VALUE
    val max: Int = Int.MAX_VALUE
}

@Generator("prime", Int::class)
internal abstract class _PrimeGenerator {
    val min: Int = 0
    val max: Int = 10000
}

@Generator("natural", Int::class)
internal abstract class _NaturalGenerator {
    val min: Int = 0
    val max: Int = Int.MAX_VALUE
    val exclude: Array<Int> = arrayOf()
}
