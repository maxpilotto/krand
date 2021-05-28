package com.maxpilotto.krand.processor

import com.maxpilotto.krand.processor.annotations.Generator
import com.maxpilotto.krand.processor.extensions.asKotlinTypeName
import com.maxpilotto.krand.processor.extensions.asNullableTypeName
import com.maxpilotto.krand.processor.extensions.getClass
import com.maxpilotto.krand.processor.models.GeneratorClassBuilder
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.asTypeName
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.type.TypeMirror

class KRandProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes() = mutableSetOf(Generator::class.java.canonicalName)

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        roundEnv?.let {
            roundEnv.getElementsAnnotatedWith(Generator::class.java).forEach { classElement ->
                val packageName = processingEnv.elementUtils.getPackageOf(classElement).toString()
                val className = classElement.simpleName.toString()
                val annotation = classElement.getAnnotation(Generator::class.java)
                val generatorBuilder = GeneratorClassBuilder(packageName, className.removePrefix(CLASS_PREFIX))
                val generatorType = try {
                    annotation.returnType as TypeMirror
                } catch (e: MirroredTypeException) {
                    e.typeMirror
                }
                val generatorTypeName = generatorType.asKotlinTypeName()

                if (classElement.enclosedElements.isEmpty()) {
                    generatorBuilder.addFunction(
                        FunSpec.builder(GENERATE_ONE)
                            .addCode("return $GENERATE_ONE(mapOf())")
                            .returns(generatorTypeName)
                            .build()
                    )
                    generatorBuilder.addFunction(
                        FunSpec.builder(GENERATE_MANY)
                            .addCode("return $GENERATE_MANY(mapOf(), count)")
                            .addParameter("count", Int::class)
                            .returns(List::class.asTypeName().parameterizedBy(generatorType.asKotlinTypeName()))
                            .build()
                    )
                    generatorBuilder.addFunction(
                        FunSpec.builder(GENERATE_STRING)
                            .addCode("return $GENERATE_STRING(mapOf())")
                            .returns(String::class)
                            .build()
                    )
                } else {
                    classElement.enclosedElements.forEach { methodElement ->
                        if (methodElement is ExecutableElement && !methodElement.simpleName.contentEquals("<init>")) {
                            generatorBuilder.addFunction(
                                GENERATE_ONE,
                                methodElement.parameters,
                                generatorTypeName,
                                false
                            )
                            generatorBuilder.addFunction(
                                GENERATE_MANY,
                                methodElement.parameters,
                                List::class.asTypeName().parameterizedBy(generatorType.asKotlinTypeName()),
                                true
                            )
                            generatorBuilder.addFunction(
                                GENERATE_STRING,
                                methodElement.parameters,
                                String::class.asTypeName(),
                                false
                            )
                        }
                    }
                }

                generatorBuilder.generatorType(
                    annotation.name,
                    generatorTypeName
                )

                generatorBuilder.build().writeTo(processingEnv.filer)
            }
        }

        return true
    }

    companion object {
        const val CLASS_PREFIX = "_"
        const val GENERATE_ONE = "one"
        const val GENERATE_MANY = "many"
        const val GENERATE_STRING = "string"
    }
}