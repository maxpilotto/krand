package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("letter", String::class)
internal interface _LetterGenerator {
    val casing: String
}

@Generator("paragraph", String::class)
internal interface _ParagraphGenerator {
    val sentences: Int
}

@Generator("sentence", String::class)
internal interface _SentenceGenerator {
    val words: Int
}

@Generator("string", String::class)
internal interface _StringGenerator {
    val length: Int
    val pool: String
    val alpha: Boolean
    val casing: String
    val symbols: Boolean
    val numeric: Boolean
}

@Generator("syllable", String::class)
internal interface _SyllableGenerator

@Generator("word", String::class)
internal interface _WordGenerator {
    val length: Int
    val syllables: Int
}

@Generator("character", String::class)
internal interface _CharacterGenerator {
    val pool: String
    val alpha: Boolean
    val numeric: Boolean
    val casing: String
    val symbols: Boolean
}