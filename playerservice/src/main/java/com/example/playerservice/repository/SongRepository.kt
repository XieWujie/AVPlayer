package com.example.playerservice.repository

import android.util.SparseArray
import androidx.core.util.set
import com.example.playerservice.http.Api
import com.example.playerservice.http.LyricEntity
import com.example.playerservice.http.Song
import com.example.playerservice.http.SongEntity
import com.example.playerservice.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

interface ISongRepository{

    fun getLyric(id:Int,lyric:TreeMap<Int,String>,callback:(t:Throwable?)->Unit)

    fun request(ids:String,songs:SparseArray<Song>,callback:(t:Throwable?)->Unit)
}

class SongRepository(val api:Api) :ISongRepository{

    override   fun getLyric(id:Int,lyric:TreeMap<Int,String>,callback:(t:Throwable?)->Unit) {
        api.getLyric(id).enqueue(object :Callback<LyricEntity>{

            override fun onFailure(call: Call<LyricEntity>, t: Throwable) {
                t.printStackTrace()
                callback(t)
            }

            override fun onResponse(call: Call<LyricEntity>, response: Response<LyricEntity>) {
                if(response.code() == 200){
                    val lyr = response.body()?.lrc?.lyric
                    if(lyr == null){
                        callback.invoke(Throwable("歌词请求错误"))
                    }else{
                        Util.lyricFormal(lyr,lyric)
                        callback.invoke(null)
                    }
                }else{
                    callback.invoke(Throwable(response.message()?:"请求错误"))
                }
            }

        })
    }

    override fun request(ids:String,songs:SparseArray<Song>,callback:(t:Throwable?)->Unit) {
        api.getSong(ids).enqueue(object :Callback<SongEntity>{
            override fun onResponse(call: Call<SongEntity>, response: Response<SongEntity>) {
                if(response.code() == 200){
                    songs.clear()
                    response.body()!!.data.forEach {
                        songs[it.id] = it
                    }
                    callback.invoke(null)
                }else{
                    callback.invoke(Throwable(response.message()?:"请求错误"))
                }
            }

            override fun onFailure(call: Call<SongEntity>, t: Throwable) {
                t.printStackTrace()
            }
    })
    }



}