package com.example.common.database

import androidx.room.RoomDatabase




class AppDbInstance {



    companion object DB{
       private lateinit var appDb:RoomDatabase
        fun registerAppDb(appDb: RoomDatabase){
            this.appDb = appDb
        }

        fun get() = appDb
    }
}

inline fun<reified T>RoomDatabase.invokeMethod(method:String):T{
    val m = this.javaClass.getDeclaredMethod(method)
    return m.invoke(this) as T
}
