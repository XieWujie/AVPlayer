package com.example.main.http

import com.example.common.adapter.AVLiveData
import com.example.main.http.entry.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MineApi{

    @GET("/user/subcount")
    fun getSubCount():AVLiveData<SubCountEntry>

    @GET("/user/playlist")
    fun userSongList(@Query("uid")uid:Int):AVLiveData<UserSongList>

    @GET("user/record")
    fun playRecord(@Query("uid")uid: Int,@Query("type")type:Int = 1):AVLiveData<PlayRecordList>

    @GET("/likelist")
    fun likeList(@Query("uid")uid:Int):AVLiveData<LikeList>

}