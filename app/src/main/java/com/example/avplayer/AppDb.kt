package com.example.avplayer

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.common.adapter.ARTypeConverter
import com.example.common.database.SongDetailDao
import com.example.common.entity.SongDetail


@Database(entities = [SongDetail::class],version = 2)
@TypeConverters(ARTypeConverter::class)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getSongDetail():SongDetailDao

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