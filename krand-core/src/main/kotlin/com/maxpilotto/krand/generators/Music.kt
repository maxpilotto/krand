package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("note", String::class)
internal interface _NoteGenerator {
    val notes: String
}

@Generator("tempo", Int::class)
internal interface _TempoGenerator {
    val min: Int
    val max: Int
}