package com.maxpilotto.krand.processor.extensions

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.KClass

internal fun KClass<*>.asNullableTypeName(): TypeName {
    return this.asTypeName().copy(true)
}