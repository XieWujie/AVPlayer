package com.example.common.adapter

import androidx.room.TypeConverter
import com.example.common.App
import com.example.common.entity.Ar
import com.google.gson.reflect.TypeToken


class ARTypeConverter {

    @TypeConverter
    fun revertArs(list:List<Ar>):String{
        val gson  = App().gson
        return gson.toJson(list)
    }
    @TypeConverter
    fun revertString(json:String):List<Ar>{
        val typeToken =object :TypeToken<List<Ar>>(){}.type
        return App().gson.fromJson(json,typeToken)
    }

}