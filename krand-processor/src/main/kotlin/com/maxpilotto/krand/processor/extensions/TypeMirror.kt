package com.maxpilotto.krand.processor.extensions

import com.maxpilotto.krand.processor.KRandProcessor
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import javax.lang.model.type.ArrayType
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass

internal fun TypeMirror.getClass(): KClass<*> {
    return when (kind) {
        TypeKind.DECLARED -> Class.forName(toString()).kotlin
        TypeKind.BOOLEAN -> Boolean::class
        TypeKind.BYTE -> Byte::class
        TypeKind.SHORT -> Short::class
        TypeKind.INT -> Int::class
        TypeKind.LONG -> Long::class
        TypeKind.CHAR -> Char::class
        TypeKind.FLOAT -> Float::class
        TypeKind.DOUBLE -> Double::class

        else -> throw Exception("Unknown type: $this, kind: $kind")
    }
}

internal fun TypeMirror.asKotlinTypeName(): TypeName {
    val typeName = asTypeName().toString()

    if (typeName.startsWith("java.util.List")) {
        throw Exception("${KRandProcessor::class} does not support List types")
    }

    return when {
        typeName == "java.lang.String" -> ClassName.bestGuess("kotlin.String")
        typeName == "java.lang.Character" -> ClassName.bestGuess("kotlin.Char")
        typeName == "java.lang.Byte" -> ClassName.bestGuess("kotlin.Byte")
        typeName == "java.lang.Short" -> ClassName.bestGuess("kotlin.Short")
        typeName == "java.lang.Integer" -> ClassName.bestGuess("kotlin.Int")
        typeName == "java.lang.Long" -> ClassName.bestGuess("kotlin.Long")
        typeName == "java.lang.Float" -> ClassName.bestGuess("kotlin.Float")
        typeName == "java.lang.Double" -> ClassName.bestGuess("kotlin.Double")
        typeName == "java.lang.Boolean" -> ClassName.bestGuess("kotlin.Boolean")
        typeName == "java.lang.Object" ->  ClassName.bestGuess("kotlin.Any")

        kind == TypeKind.ARRAY -> {
            val componentType = (this as ArrayType).componentType.getClass()

            Array::class.parameterizedBy(componentType)
        }

        else -> asTypeName()
    }
}