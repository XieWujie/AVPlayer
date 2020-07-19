package com.example.conmon.util

import java.security.MessageDigest
import android.text.TextUtils
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and


class Md5Encrypt {
    companion object{

        fun getMd5(string: String,subLength:Int): String {
            if (TextUtils.isEmpty(string)) {
                return ""
            }
            var md5: MessageDigest? = null
            try {
                md5 = MessageDigest.getInstance("MD5")
                val bytes = md5!!.digest(string.toByteArray())
                var result = ""
                for (b in bytes) {
                    var temp = Integer.toHexString(b.toInt() and 0xff)
                    if (temp.length == 1) {
                        temp = "0$temp"
                    }
                    result += temp
                }
                return result.substring(0,subLength)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return ""
        }

    }
}