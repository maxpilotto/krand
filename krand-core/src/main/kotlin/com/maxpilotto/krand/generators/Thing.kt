package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("animal", String::class)
internal interface _AnimalGenerator {
    val type: String
}

