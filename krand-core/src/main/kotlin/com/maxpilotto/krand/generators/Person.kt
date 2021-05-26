package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator
import java.util.*

@Generator("age", Int::class)
interface _AgeGenerator {
    fun gen(
        type: String = "adult"      //child, teen, adult, senior
    )
}

@Generator("birthday", Date::class)
interface _BirthdayGenerator {
    fun gen(
        type: String = "adult"      //child, teen, adult, senior
    )
}

@Generator("cf", String::class)
interface _CFGenerator {
    fun gen(
        first: String = "",
        last: String = "",
        gender: String = "",
        birthday: String = "",
        city: String = ""
    )
}

@Generator("cpf", String::class)
interface _CPFGenerator

@Generator("first", String::class)
interface _FirstnameGenerator {
    fun gen(
        gender: String = "",
        nationality: String = ""    //'us', 'it'
    )
}

@Generator("gender", String::class)
interface _GenderGenerator {
    fun gen(
        extraGenders: Array<String> = arrayOf()
    )
}

@Generator("last", String::class)
interface _LastnameGenerator {
    fun gen(
        nationality: String = ""  //FIXME: Enum  //'en', 'it', 'nl', 'uk', 'de', 'jp', 'es', 'fr'
    )
}

@Generator("name", String::class)
interface _NameGenerator {
    fun gen(
        middle: Boolean = true,
        middleInitial: Boolean = true,
        prefix: Boolean = true,
        suffix: Boolean = true,
        gender: String = "",
        nationality: String = ""  //FIXME: Enum  //'en', 'it'
    )
}

@Generator("prefix", String::class)
interface _PrefixGenerator {
    fun gen(
        full: Boolean = true,
        gender: String = ""
    )
}

@Generator("ssn", String::class)
interface _SSNGenerator {
    fun gen(
        ssnFour: Boolean = true,
        dashes: Boolean = false
    )
}

@Generator("suffix", String::class)
interface _SuffixGenerator {
    fun gen(
        full: Boolean = true
    )
}