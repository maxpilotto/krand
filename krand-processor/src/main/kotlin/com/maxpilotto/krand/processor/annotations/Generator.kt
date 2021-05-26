package com.maxpilotto.krand.processor.annotations

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Generator(
    val name: String,
    val returnType: KClass<*>
)
