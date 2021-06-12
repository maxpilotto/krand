package com.maxpilotto.krand.processor

import com.maxpilotto.krand.processor.annotations.Generator
import com.maxpilotto.krand.processor.extensions.asKotlinTypeName
import com.maxpilotto.krand.processor.extensions.getPropertyGetters
import com.maxpilotto.krand.processor.models.GeneratorClassBuilder
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
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

                // Override the "one" method, which is the primary method used for generation
                generatorBuilder.addPrimaryGenerator(
                    classElement.getPropertyGetters(),
                    annotation.name,
                    generatorType
                )

                // Loop through all getters of the interface or abstract class
                classElement.getPropertyGetters().forEach { getter ->
                    // Look up for a property that holds the default value
                    val property = classElement.enclosedElements.find {
                        it is VariableElement && it.simpleName.toString() == getter.simpleName.toString()
                            .removePrefix("get")
                            .decapitalize()
                    } as VariableElement?

                    generatorBuilder.addProperty(getter, property)
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