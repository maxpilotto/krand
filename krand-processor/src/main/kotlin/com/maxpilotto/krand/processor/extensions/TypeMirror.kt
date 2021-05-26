package com.maxpilotto.krand.processor.extensions

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
    val typeName = asTypeName()

    return when (kind) {
        //TODO: Add support for lists too
        TypeKind.ARRAY -> {
            val componentType = (this as ArrayType).componentType.getClass()

            Array::class.parameterizedBy(componentType)
        }

        else -> when (typeName.toString()) {
            "java.lang.String" -> ClassName.bestGuess("kotlin.String")
            "java.lang.Character" -> ClassName.bestGuess("kotlin.Char")
            "java.lang.Byte" -> ClassName.bestGuess("kotlin.Byte")
            "java.lang.Short" -> ClassName.bestGuess("kotlin.Short")
            "java.lang.Integer" -> ClassName.bestGuess("kotlin.Int")
            "java.lang.Long" -> ClassName.bestGuess("kotlin.Long")
            "java.lang.Float" -> ClassName.bestGuess("kotlin.Float")
            "java.lang.Double" -> ClassName.bestGuess("kotlin.Double")
            "java.lang.Boolean" -> ClassName.bestGuess("kotlin.Boolean")

            else -> typeName
        }
    }
}