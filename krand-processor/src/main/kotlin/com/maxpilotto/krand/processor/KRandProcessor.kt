package com.maxpilotto.krand.processor

import com.maxpilotto.krand.processor.annotations.Generator
import com.maxpilotto.krand.processor.extensions.asKotlinTypeName
import com.maxpilotto.krand.processor.models.GeneratorClassBuilder
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asTypeName
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
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
                }.asKotlinTypeName()

                if (classElement.enclosedElements.isEmpty()) {
                    generatorBuilder.addFunction(
                        FunSpec.builder(FUNCTION_GENERATE)
                            .addCode("return $FUNCTION_GENERATE(mapOf())")
                            .returns(generatorType)
                            .build()
                    )
                } else {
                    classElement.enclosedElements.forEach { methodElement ->
                        if (methodElement is ExecutableElement && !methodElement.simpleName.contentEquals("<init>")) {
                            generatorBuilder.addFunction(
                                methodElement.simpleName,
                                methodElement.parameters,
                                generatorType
                            )
                        }
                    }
                }

                generatorBuilder.generatorType(
                    annotation.name,
                    generatorType
                )

                generatorBuilder.build().writeTo(processingEnv.filer)
            }
        }

        return true
    }

    companion object {
        const val CLASS_PREFIX = "_"
        const val FUNCTION_GENERATE = "gen"
    }
}