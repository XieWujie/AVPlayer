package com.example.songlist.di

import com.example.songlist.http.SongSquareApi
import com.xie.di.DiBus
import com.xie.di.Provide
import com.xie.di.Service
import retrofit2.Retrofit

private const val SONG_LIST_MODULE = "song_list_module"

@Service
class SongListDi {

    private val retrofit:Retrofit = DiBus.load()

    @Provide
    fun provideSongApi(): SongSquareApi = retrofit.create( SongSquareApi::class.java)
}