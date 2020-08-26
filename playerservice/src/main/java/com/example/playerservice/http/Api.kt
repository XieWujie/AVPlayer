package com.example.playerservice.http

import com.example.common.entity.SongsDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/song/url")
    fun getSong(@Query("id")id:String):Call<SongEntity>

    @GET("/lyric")
    fun getLyric(@Query("id")id:Int):Call<LyricEntity>

}