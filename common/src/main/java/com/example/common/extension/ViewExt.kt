package com.example.common.extension

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun runOnMainThread(f:()->Unit){
    Handler(Looper.getMainLooper()).post(f)
}