package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("letter", String::class)
interface _LetterGenerator {
    val casing: String
}

@Generator("paragraph", String::class)
interface _ParagraphGenerator {
    val sentences: Int
}

@Generator("sentence", String::class)
interface _SentenceGenerator {
    val words: Int
}

@Generator("string", String::class)
interface _StringGenerator {
    val length: Int
    val pool: String
    val alpha: Boolean
    val casing: String
    val symbols: Boolean
}

@Generator("syllable", String::class)
interface _SyllableGenerator

@Generator("word", String::class)
interface _WordGenerator {
    val length: Int
}

@Generator("character", String::class)
interface _CharacterGenerator {
    val pool: String
    val alpha: Boolean
    val numeric: Boolean
    val casing: String
    val symbols: Boolean
}