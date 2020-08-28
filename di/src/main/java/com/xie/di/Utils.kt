package com.xie.di

internal object Utils {

    fun getModuleName(path:String):String{
       return path.substringBeforeLast(".").substringAfterLast(".")
    }

    fun getSignatureFromArgs(args: Array<out Any>):String{
        if(args.size == 1){
            return args[0]::class.java.canonicalName!!
        }
        val builder = StringBuilder()
        for(arg in args){
            builder.append(arg::class.java.canonicalName)
            builder.append(",")
        }
        if(args.isNotEmpty()){
            builder.deleteCharAt(builder.length-1)
        }
        return builder.toString()
    }
}