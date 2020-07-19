package com.example.conmon.extension

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
