package com.example.common.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.common.entity.SongDetail

@Dao
interface SongDetailDao{


    @Query("SELECT * FROM songdetail where id=:id")
    fun queryById(id:Int):LiveData<SongDetail>

    @Query("SELECT * FROM songdetail where id IN (:ids)")
    fun queryByIds(ids:List<Int>):LiveData<List<SongDetail>>

    @Insert
    fun insert(song:SongDetail)

    @Insert
    fun insert(songs:List<SongDetail>)
}