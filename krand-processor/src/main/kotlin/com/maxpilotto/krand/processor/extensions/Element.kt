package com.maxpilotto.krand.processor.extensions

import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

internal fun Element.getProperties(): List<ExecutableElement> {
    return enclosedElements.filterIsInstance<ExecutableElement>()
        .filter { it.simpleName.startsWith("get") }
}