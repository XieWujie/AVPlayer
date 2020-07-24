package com.example.db_compiler

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.TYPE,AnnotationTarget.FILE)
annotation class Entry(val name:String = "")

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class Ignore()

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class PrimaryKey()


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class Name(val value:String = "")

interface TableLoad{

    fun load(tables:List<String>)

}