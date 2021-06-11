package com.maxpilotto.krand.processor.extensions

import com.squareup.kotlinpoet.TypeName

internal fun TypeName.asNullableTypeName(): TypeName {
    return copy(true)
}