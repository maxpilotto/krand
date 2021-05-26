package com.maxpilotto.krand.processor.models

import com.maxpilotto.krand.processor.KRandProcessor
import com.maxpilotto.krand.processor.extensions.asKotlinTypeName
import com.maxpilotto.krand.processor.extensions.asNullableTypeName
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import javax.lang.model.element.Name
import javax.lang.model.element.VariableElement

internal class GeneratorClassBuilder(
    packageName: String,
    className: String
) {
    private val file: FileSpec.Builder
    private val _class: TypeSpec.Builder

    init {
        val seedProp = ParameterSpec.builder("seed", Any::class.asNullableTypeName())
            .defaultValue("null")
            .build()

        val primaryConstructor = FunSpec.constructorBuilder()
            .addParameter(seedProp)
            .build()

        file = FileSpec.builder(packageName, className)
        _class = TypeSpec.classBuilder(className)
            .primaryConstructor(primaryConstructor)
    }

    fun generatorType(name: String, type: TypeName) = apply {
        val superClass = ClassName.bestGuess("com.maxpilotto.krand.models.BaseGenerator")
            .parameterizedBy(type)

        _class.superclass(superClass)
            .addSuperclassConstructorParameter("\"$name\"")
            .addSuperclassConstructorParameter("seed")
    }

    fun addFunction(funSpec: FunSpec) = apply {
        _class.addFunction(funSpec)
    }

    fun addFunction(name: Name, parameters: List<VariableElement>, returnType: TypeName) = apply {
        if (parameters.isNotEmpty()) {
            val func = FunSpec.builder(name.toString())
                .returns(returnType)
            var body = "return ${KRandProcessor.FUNCTION_GENERATE}("

            body += parameters.joinToString(",", "mapOf(", ")", transform = {
                "\"${it.simpleName}\" to ${it.simpleName}"
            })
            parameters.forEach { p ->
                val param = ParameterSpec.builder(p.simpleName.toString(), p.asType().asKotlinTypeName().copy(true))
                    .defaultValue("null")
                    .build()

                func.addParameter(param)
            }
            body += ")"

            _class.addFunction(
                func.addCode(body).build()
            )
        }
    }

    fun build(): FileSpec {
        return file.addType(_class.build()).build()
    }
}