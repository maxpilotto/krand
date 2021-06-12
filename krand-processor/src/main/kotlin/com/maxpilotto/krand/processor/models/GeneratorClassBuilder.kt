package com.maxpilotto.krand.processor.models

import com.maxpilotto.krand.processor.extensions.asKotlinTypeName
import com.maxpilotto.krand.processor.extensions.asNullableTypeName
import com.maxpilotto.krand.processor.extensions.asPropertyName
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.ArrayType
import javax.lang.model.type.TypeKind

internal class GeneratorClassBuilder(
    private val packageName: String,
    private val className: String,
    private val genericType: TypeName
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
        val superClass = ClassName.bestGuess(GENERATOR_SUPERCLASS)
            .parameterizedBy(genericType)

        file = FileSpec.builder(packageName, className).indent("\t")
        _class = TypeSpec.classBuilder(className)
            .primaryConstructor(primaryConstructor)
            .superclass(superClass)
            .addSuperclassConstructorParameter("seed")
    }

    fun addProperty(propertyGetter: ExecutableElement, property: VariableElement?) = apply {
        val propName = propertyGetter.simpleName.asPropertyName()
        val propType = propertyGetter.returnType.asKotlinTypeName().asNullableTypeName()
        val prop = PropertySpec.builder(propName, propType)
            .mutable(true)
            .setter(
                FunSpec.setterBuilder()
                    .addModifiers(KModifier.PRIVATE)
                    .build()
            )
            .initializer(property?.constantValue.toString())
            .build()
        val func: FunSpec

        if (propertyGetter is VariableElement) {
            println("Default value for $propName: ${propertyGetter.constantValue}")
        }

        when (propertyGetter.returnType.kind) {
            TypeKind.ARRAY -> {
                val componentType = (propertyGetter.returnType as ArrayType).componentType
                val param = ParameterSpec.builder(propName, componentType.asKotlinTypeName())
                    .addModifiers(KModifier.VARARG)
                    .build()
                func = FunSpec.builder(propName)
                    .addParameter(param)
                    .addCode(
                        """return apply{ 
                        |   this.$propName = $propName.toTypedArray()
                        |}""".trimMargin()
                    )
                    .returns(ClassName.bestGuess("$packageName.$className"))
                    .build()

                file.addImport("com.maxpilotto.krand.extensions", "toTypedArray")
            }
            TypeKind.BOOLEAN -> {
                val booleanProp = ParameterSpec.builder(propName, propType.asNullableTypeName())
                    .defaultValue("true")
                    .build()

                func = FunSpec.builder(propName)
                    .addParameter(booleanProp)
                    .addCode(
                        """return apply{ 
                        |   this.$propName = $propName 
                        |}""".trimMargin()
                    )
                    .returns(ClassName.bestGuess("$packageName.$className"))
                    .build()
            }

            else -> {
                func = FunSpec.builder(propName)
                    .addParameter(propName, propType)
                    .addCode(
                        """return apply{ 
                        |   this.$propName = $propName 
                        |}""".trimMargin()
                    )
                    .returns(ClassName.bestGuess("$packageName.$className"))
                    .build()
            }
        }

        _class.addProperty(prop)
        _class.addFunction(func)
    }

    fun addPrimaryGenerator(properties: List<ExecutableElement>, generatorName: String, returnType: TypeName) = apply {
        val func = FunSpec.builder("one")
            .addModifiers(KModifier.OVERRIDE)
            .returns(returnType)
        val body = properties.joinToString(",", "return execute(\"$generatorName\", mapOf(", "))") {
            val prop = it.simpleName.asPropertyName()

            "\"$prop\" to $prop"
        }

        func.addCode(body)

        _class.addFunction(func.build())
    }

    fun build(): FileSpec {
        return file.addType(_class.build()).build()
    }

    companion object {
        const val GENERATOR_SUPERCLASS = "com.maxpilotto.krand.models.AbstractGenerator"
    }
}