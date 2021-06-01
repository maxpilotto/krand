package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator
import java.util.*

@Generator("age", Int::class)
interface _AgeGenerator {
    val type: String
}

@Generator("birthday", Date::class)
interface _BirthdayGenerator {
    val type: String
}

@Generator("cf", String::class)
interface _CFGenerator {
    val first: String
    val last: String
    val gender: String
    val birthday: String
    val city: String
}

@Generator("cpf", String::class)
interface _CPFGenerator

@Generator("first", String::class)
interface _FirstnameGenerator {
    val gender: String
    val nationality: String    //'us', 'it'
}

@Generator("gender", String::class)
interface _GenderGenerator {
    val extraGenders: Array<String>
}

@Generator("last", String::class)
interface _LastnameGenerator {
    val nationality: String    //'en', 'it', 'nl', 'uk', 'de', 'jp', 'es', 'fr'
}

@Generator("name", String::class)
interface _NameGenerator {
    val middle: Boolean
    val middleInitial: Boolean
    val prefix: Boolean
    val suffix: Boolean
    val gender: String
    val nationality: String    //'en', 'it'
}

@Generator("prefix", String::class)
interface _PrefixGenerator {
    val full: Boolean
    val gender: String
}

@Generator("ssn", String::class)
interface _SSNGenerator {
    val ssnFour: Boolean
    val dashes: Boolean
}

@Generator("suffix", String::class)
interface _SuffixGenerator {
    val full: Boolean
}