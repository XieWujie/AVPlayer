package com.example.route

import android.content.Intent
import android.os.Bundle

class RouteInfo (private val intent: Intent,private val execute:()->Unit){


    infix fun String.with(value:String){
        intent.putExtra(this,value)
    }

    infix fun String.with(value:Boolean){
        intent.putExtra(this,value)
    }

    infix fun String.with(value:Intent){
        intent.putExtra(this,value)
    }

    infix fun String.with(value:Bundle){
        intent.putExtra(this,value)
    }
    infix fun String.with(value: Int){

    }

    fun execute() = execute.invoke()
}