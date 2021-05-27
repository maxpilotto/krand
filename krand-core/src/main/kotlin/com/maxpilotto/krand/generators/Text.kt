package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("letter", String::class)
interface _LetterGenerator {
    fun gen(
        casing: String = "lower"    //TODO: Enum
    )
}

@Generator("paragraph", String::class)
interface _ParagraphGenerator {
    fun gen(
        sentences: Int = 1
    )
}

@Generator("sentence", String::class)
interface _SentenceGenerator {
    fun gen(
        words: Int = 15 //TODO: Default parameters can't be used with the annotation processor
    )
}

@Generator("string", String::class)
interface _StringGenerator {
    fun gen(
        length: Int = 10,
        pool: String = "",
        alpha: Boolean = true,
        casing: String = "",
        symbols: Boolean = true
    )
}

@Generator("syllable", String::class)
interface _SyllableGenerator

@Generator("word", String::class)
interface _WordGenerator {
    fun gen(
        length: Int = 5
    )
}

@Generator("character", String::class)
interface _CharacterGenerator {
    fun gen(
        pool: String = "",
        alpha: Boolean = true,
        numeric: Boolean = true,
        casing: String = "lower",
        symbols: Boolean = true
    )
}

