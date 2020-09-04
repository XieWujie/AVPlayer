package com.example.playerservice.http

import com.dibus.DiBus
import com.dibus.Provide
import com.dibus.Service
import com.example.common.HttpDiService
import dibus.common.HttpDiServiceCreator
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/song/url")
    fun getSong(@Query("id")id:String):Call<SongEntity>

    @GET("/lyric")
    fun getLyric(@Query("id")id:Int):Call<LyricEntity>

}

@Service
class ApiProvide{
    @Provide
    fun provideApi():Api{
        HttpDiServiceCreator.get()
        val retrofit = DiBus.load<Retrofit>()
        return retrofit.create(Api::class.java)
    }

}
