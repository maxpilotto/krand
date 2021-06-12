package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("coin", String::class)
internal interface _CoinGenerator

@Generator("d4", Int::class)
internal interface _D4Generator

@Generator("d6", Int::class)
internal interface _D6Generator

@Generator("d8", Int::class)
internal interface _D8Generator

@Generator("d10", Int::class)
internal interface _D10Generator

@Generator("d12", Int::class)
internal interface _D12Generator

@Generator("d20", Int::class)
internal interface _D20Generator

@Generator("d30", Int::class)
internal interface _D30Generator

@Generator("d100", Int::class)
internal interface _D100Generator

@Generator("guid", String::class)
internal interface _GUIDGenerator {
    val version: Int
}

@Generator("hash", String::class)
internal interface _HashGenerator {
    val length: Int
    val casing: String
}

@Generator("normal", Float::class)
internal interface _NormalGenerator {
    val mean: Int
    val dev: Int
}

@Generator("radio", String::class)
internal interface _RadioCallSignGenerator {
    val side: String   //east, weat
}