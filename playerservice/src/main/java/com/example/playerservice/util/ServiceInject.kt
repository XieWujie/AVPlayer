package com.example.playerservice.util

import android.content.Context
import com.example.playerservice.core.AVPlayer
import com.example.playerservice.core.PlayerConnection
import com.example.playerservice.core.SongHouse
import com.example.playerservice.http.Api
import com.example.playerservice.repository.SongRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceInject {

    fun inject(context: Context):PlayerConnection{

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        val repository = SongRepository(api)
        val avPlay = AVPlayer(context, SongHouse(repository,context))
        return PlayerConnection(context,avPlay)
    }
}