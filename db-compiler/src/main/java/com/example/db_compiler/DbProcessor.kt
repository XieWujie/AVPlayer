package com.example.db_compiler

import com.google.auto.service.AutoService
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import com.squareup.javapoet.ParameterizedTypeName
import java.lang.StringBuilder
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import kotlin.collections.ArrayList

@AutoService(Processor::class)
class DbProcessor :AbstractProcessor(){

    override fun process(elements: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        if(elements.isEmpty()){
            return false
        }
        val typeElement = roundEnvironment.getElementsAnnotatedWith(Entry::class.java)
        val sqls = ArrayList<String>()
        for(element in typeElement){
            val typeElement = element as TypeElement
            val sql = generateSQL(typeElement)
            sqls.add(sql)

        }
    }

    fun generateSQL(element:TypeElement):String{
        var tableName = element.getAnnotation(Entry::class.java).name
        if(tableName.isEmpty()){
            tableName = element.simpleName.toString()
        }
        val table = Class.forName(element.qualifiedName.toString())
        val fields = table.declaredFields
        val builder = StringBuilder()
        builder.append("Create table $tableName(")
        for(field in fields){
            field.annotatedType.getDeclaredAnnotation(Ignore::class.java)?:continue
            val type = field.genericType.typeName
            val lastIndex = type.lastIndexOf(".")
            val typeName = type.substring(lastIndex+1)
            var fieldName = field.annotatedType.getDeclaredAnnotation(Name::class.java).value
            if(fieldName.isEmpty()){
                fieldName = field.name
            }
            val key = field.annotatedType.getDeclaredAnnotation(PrimaryKey::class.java)
            val row = when(key){
                null->builder.append("$fieldName $tableName,")
                else->builder.append("$fieldName $tableName,")
            }
        }
        builder.append(")")
        return builder.toString()
    }

    fun generateJava(sqls:List<String>){
        val parameterType = ParameterizedTypeName.get(
            ClassName.get(List::class.java),
            ClassName.get(String::class.java)
        )
        val parameter = ParameterSpec.builder(parameterType,"list").build()
        val methodBuilder = MethodSpec.methodBuilder("load")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameter)
        for(sql in sqls){
            methodBuilder.addStatement("list.add(\$S)",sql)
        }
        val superType = ClassName.get("")
    }
}
