package com.xie.di

import com.squareup.javapoet.*
import java.io.IOException
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier

interface ViewModelCreator<T>{
    fun create():T
}

class ViewModelGenerator (private val filer: Filer, private val info: BusAwareInfo, private val createMethod: MethodSpec){

    val name:String ="${Utils.getClassNameFromPath(info.receiverClass).second}Factory"



    private fun generateCreate():MethodSpec{
        val viewModelType = ClassName.get("androidx.lifecycle","ViewModel")
        val returnType = TypeVariableName.get("T",viewModelType)
        val pType = ParameterizedTypeName.get(ClassName.get(Class::class.java),returnType)
        return MethodSpec.methodBuilder("create")
            .addAnnotation(Override::class.java)
            .addTypeVariable(returnType)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(pType,"modelType")
            .returns(returnType)
            .addStatement("return (\$T)create()",returnType)
            .build()
    }



    fun generate(){
       val fetcherType = ClassName.get(Fetcher::class.java)
        val instanceType =Utils.getClassName(info.receiverClass)
        val field = FieldSpec.builder(fetcherType,"fetcher")
            .build()
        val instance = FieldSpec.builder(instanceType,"instance").build()
        val cons = MethodSpec.constructorBuilder()
            .addParameter(fetcherType,"fetcher")
            .addStatement("this.fetcher = fetcher")
            .build()
        val sp = ParameterizedTypeName.get(ClassName.get(ViewModelCreator::class.java),instanceType)
        val superClass = ClassName.get("androidx.lifecycle.ViewModelProvider","NewInstanceFactory")
        val type = TypeSpec.classBuilder(name)
            .addField(field)
            .addField(instance)
            .addMethod(cons)
            .addMethod(createMethod)
            .addMethod(generateCreate())
            .superclass(superClass)
            .addSuperinterface(sp)
            .build()
        try {
            JavaFile.builder(BASE_PACKAGE, type).build()
                .writeTo(filer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}