package com.example.avplayer

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [],version = 1)
abstract class AppDatabase: RoomDatabase(){

    companion object {

        @Volatile
        private var instance:AppDatabase? = null

        fun getInstance(context: Context):AppDatabase{
            return instance?: synchronized(this){
                instance?: buildInstance(context).also { instance = it }
            }
        }
        private fun buildInstance(context: Context):AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java, "av_player")
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("AppDb","database created")
                    }
                }).build()
        }
    }
}