@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.playerservice.util

import android.annotation.SuppressLint
import okhttp3.Request
import java.util.*
import java.util.regex.Pattern

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object Util {

    private const val pattern = "\\[(\\d{2}):(\\d{2}).(\\d{2})](.*)"
    @SuppressLint("UseSparseArrays")
    fun lyricFormal(content: String, lyrics: TreeMap<Int, String>) {
        val rows = content.split("\n")
        var time = 0
        val cilent = Request.Builder()
        val p = Pattern.compile(pattern)
        for (row in rows) {
            time = 0
            val m = p.matcher(row)
            if(!m.find())continue
            if(m.groupCount()!=4)continue
            m.apply {
                time+=m.group(1).toInt()*60000
                time+=m.group(2).toInt()*1000
                time+=m.group(3).toInt()
                lyrics[time] = group(4)
            }
        }
    }
}