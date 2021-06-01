package com.maxpilotto.krand.processor

import com.maxpilotto.krand.processor.annotations.Generator
import com.maxpilotto.krand.processor.extensions.asKotlinTypeName
import com.maxpilotto.krand.processor.extensions.getProperties
import com.maxpilotto.krand.processor.models.GeneratorClassBuilder
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
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
                val generatorType = try {
                    annotation.returnType as TypeMirror
                } catch (e: MirroredTypeException) {
                    e.typeMirror
                }.asKotlinTypeName()
                val generatorBuilder = GeneratorClassBuilder(
                    packageName,
                    className.removePrefix(CLASS_PREFIX),
                    generatorType
                )

                generatorBuilder.addPrimaryGenerator(
                    classElement.getProperties(),
                    annotation.name,
                    generatorType
                )

                classElement.getProperties().forEach {
                    generatorBuilder.addProperty(it)
                }

                generatorBuilder.build().writeTo(processingEnv.filer)
            }
        }

        return true
    }

    companion object {
        const val CLASS_PREFIX = "_"
    }
}