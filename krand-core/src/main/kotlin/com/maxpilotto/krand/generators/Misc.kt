package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("coin", String::class)
interface _CoinGenerator

@Generator("d4", Int::class)
interface _D4Generator

@Generator("d6", Int::class)
interface _D6Generator

@Generator("d8", Int::class)
interface _D8Generator

@Generator("d10", Int::class)
interface _D10Generator

@Generator("d12", Int::class)
interface _D12Generator

@Generator("d20", Int::class)
interface _D20Generator

@Generator("d30", Int::class)
interface _D30Generator

@Generator("d100", Int::class)
interface _D100Generator

@Generator("guid", String::class)
interface _GUIDGenerator {
    fun gen(
        version: Int = 5
    )
}

@Generator("hash", String::class)
interface _HashGenerator {
    fun gen(
        length: Int = 40,
        casing: String = "lower"
    )
}

@Generator("normal", Float::class)
interface _NormalGenerator {
    fun gen(
        mean: Int = 0,
        dev: Int = 1
    )
}

@Generator("radio", String::class)
interface _RadioCallSignGenerator {
    fun gen(
        side: String = "east"   //east, weat
    )
}