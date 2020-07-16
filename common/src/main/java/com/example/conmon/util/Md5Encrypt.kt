package com.example.conmon.util

import java.security.MessageDigest

class Md5Encrypt {
    companion object{

        fun encrypt(value:String):String{
            if(value.isBlank()){
                throw IllegalArgumentException("value can not be empty")
            }
            val md = MessageDigest.getInstance("MD5")
            md.update(value.toByteArray())
            return md.digest().toString()
        }
    }
}