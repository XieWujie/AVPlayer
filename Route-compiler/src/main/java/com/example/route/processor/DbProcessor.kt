//package com.example.route.processor
//
//import com.example.route.PACKAGE_PATH
//import com.example.route.annotation.Entry
//import com.example.route.annotation.Ignore
//import com.example.route.annotation.Name
//import com.example.route.annotation.PrimaryKey
//import com.google.auto.service.AutoService
//import com.squareup.javapoet.*
//import java.io.IOException
//import javax.annotation.processing.*
//import javax.lang.model.element.Modifier
//import javax.lang.model.element.TypeElement
//import javax.lang.model.type.MirroredTypeException
//import javax.management.relation.RoleUnresolved
//import kotlin.reflect.KClass
//
//@AutoService(Processor::class)
//class DbProcessor :AbstractProcessor(){
//
//    private lateinit var filer: Filer
//    private lateinit var moduleName:String
//    private  var firstTableName:String = ""
//
//    override fun init(processingEnvironment: ProcessingEnvironment) {
//        filer = processingEnvironment.filer
//        moduleName = processingEnvironment.options["moduleName"]?:""
//    }
//    override fun process(elements: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
//        if(elements.isEmpty()){
//            return false
//        }
//        val typeElement = roundEnvironment.getElementsAnnotatedWith(Entry::class.java)
//        val sqls = ArrayList<String>()
//        for(element in typeElement){
//            val typeElement = element as TypeElement
//            val sql = generateSQL(typeElement)
//            sqls.add(sql)
//        }
//        generateJava(sqls)
//        return true
//    }
//
//    override fun getSupportedAnnotationTypes() = setOf(Entry::class.java.canonicalName)
//
//    fun generateSQL(element:TypeElement):String{
//        var tableName = element.getAnnotation(Entry::class.java).name
//        if(tableName.isEmpty()){
//            tableName = element.simpleName.toString()
//        }
//        if(firstTableName.isEmpty()){
//            firstTableName = tableName
//        }
//        val table = getClass(element)!!.java
//        val fields = table.fields
//        val builder = StringBuilder()
//        builder.append("Create table $tableName(")
//        for(field in fields){
//            field.annotatedType.getAnnotation(Ignore::class.java)?:continue
//            if(!(field.type.isPrimitive || String::class.java.isAssignableFrom(field.type))){
//                continue
//            }
//            val type = field.genericType.toString()
//            val lastIndex = type.lastIndexOf(".")
//            val typeName = type.substring(lastIndex+1)
//            var fieldName = field.annotatedType.getAnnotation(Name::class.java).value
//            if(fieldName.isEmpty()){
//                fieldName = field.name
//            }
//            val key = field.annotatedType.getAnnotation(PrimaryKey::class.java)
//            val row = when(key){
//                null->builder.append("$fieldName $typeName,")
//                else->builder.append("$fieldName $tableName primary key,")
//            }
//            builder.append(row)
//        }
//        builder.append(")")
//        return builder.toString()
//    }
//
//    private fun getClass(typeElement: TypeElement):KClass<*>?{
//        try {
//           return typeElement.getAnnotation(Entry::class.java).clazz
//        }catch (e: MirroredTypeException){
//             e.typeMirror.kind.
//        }
//        return null
//    }
//
//    fun generateJava(sqls:List<String>){
//        val parameterType = ParameterizedTypeName.get(
//            ClassName.get(List::class.java),
//            ClassName.get(String::class.java)
//        )
//        val parameter = ParameterSpec.builder(parameterType,"list").build()
//        val methodBuilder = MethodSpec.methodBuilder("load")
//            .addAnnotation(Override::class.java)
//            .addModifiers(Modifier.PUBLIC)
//            .addParameter(parameter)
//        for(sql in sqls){
//            methodBuilder.addStatement("list.add(\$S)",sql)
//        }
//        val superType = ClassName.get("$PACKAGE_PATH/core","ITable")
//        val clazzName = if(moduleName.isEmpty()) "${firstTableName}Tables" else "${moduleName}Tables"
//        val clazz = TypeSpec.classBuilder(clazzName)
//            .addSuperinterface(superType)
//            .addModifiers(Modifier.PUBLIC)
//            .addMethod(methodBuilder.build())
//            .build()
//        try {
//            //生成java文件，PACKAGE_OF_GENERATE_FILE就是生成文件需要的路径
//            JavaFile.builder(PACKAGE_PATH,clazz).build()
//                .writeTo(filer)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//}
