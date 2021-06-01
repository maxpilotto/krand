package com.maxpilotto.krand.processor.extensions

import javax.lang.model.element.Name

internal fun Name.asPropertyName(): String {
    return replace(Regex("^(get|set)"), "").decapitalize()
}