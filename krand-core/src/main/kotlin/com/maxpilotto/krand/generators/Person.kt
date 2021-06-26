package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator
import java.util.*

@Generator("age", Int::class)
internal interface _AgeGenerator {
    val type: String
}

@Generator("birthday", Date::class)
internal interface _BirthdayGenerator {
    val type: String
}

@Generator("cf", String::class)
internal interface _CFGenerator {
    val first: String
    val last: String
    val gender: String
    val birthday: String
    val city: String
}

@Generator("cpf", String::class)
internal interface _CPFGenerator

@Generator("first", String::class)
internal interface _FirstnameGenerator {
    val gender: String
    val nationality: String    //'en', 'it', 'nl', 'fr'
}

@Generator("gender", String::class)
internal interface _GenderGenerator {
    val extraGenders: Array<String>
}

@Generator("last", String::class)
internal interface _LastnameGenerator {
    val nationality: String    //'en', 'it', 'nl', 'uk', 'de', 'jp', 'es', 'fr'
}

@Generator("name", String::class)
internal interface _NameGenerator {
    val middle: Boolean
    val middleInitial: Boolean
    val prefix: Boolean
    val suffix: Boolean
    val gender: String
    val nationality: String    //'en', 'it'
}

@Generator("prefix", String::class)
internal interface _PrefixGenerator {
    val full: Boolean
    val gender: String
}

@Generator("ssn", String::class)
internal interface _SSNGenerator {
    val ssnFour: Boolean
    val dashes: Boolean
}

@Generator("suffix", String::class)
internal interface _SuffixGenerator {
    val full: Boolean
}