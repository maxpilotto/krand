package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("animal", String::class)
interface _AnimalGenerator {
    fun gen(
        type: String = ""  //TODO: Enum  //ocean, desert, grassland, forest, farm, pet, and zoo
    )
}

