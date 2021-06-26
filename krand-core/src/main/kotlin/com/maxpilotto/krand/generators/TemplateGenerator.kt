package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.AbstractGenerator

class TemplateGenerator(seed: Any? = null) : AbstractGenerator<String>(seed) {
    var template: String = "{AA####}"
        private set

    override fun one(): String {
        return execute("template", listOf(template), mapOf())
    }

    /**
     * The following replacement characters can be used in a replacement sequence:
     * + "#": a random digit
     * + "a": a random lower case letter
     * + "A": a random upper case letter
     */
    fun template(template: String) = apply {
        this.template = template
    }
}