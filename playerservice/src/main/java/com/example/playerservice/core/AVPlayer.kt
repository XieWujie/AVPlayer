package com.example.playerservice.core

import android.content.Context
import android.media.MediaPlayer
import com.example.conmon.extension.toast
import com.example.playerservice.IAVService
import com.example.playerservice.http.Song

interface IAVPlayer{

    fun pause()
    fun stop()
    fun start()
    fun seekTo(time:Int)
    fun next()
    fun pre()
    fun play(id:Int)
    fun getHouse():SongHouse
    fun registerCallback(callback:IAVService)

}
class AVPlayer(val context: Context,private val house: SongHouse) : IAVPlayer,MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener {


    private var currentMedia:MediaPlayer? = null
    private var playCallback:IAVService? = null

    override fun start() {
        currentMedia?.start()
        playCallback?.started(house.currentPlayId)
    }

    override fun registerCallback(callback: IAVService) {
        playCallback = callback
        house.registerCallback(callback)
    }


     fun preparedSong(song:Song) {
         releaseCurrentMedia()
        val url = song?.url
         if(url == null){
             context.toast("找不到歌曲URL")
             return
         }
         MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener(this@AVPlayer)
        }
    }



    override fun getHouse(): SongHouse {
        return house
    }

    override fun onPrepared(mp: MediaPlayer?) {
        currentMedia = mp?:return
        currentMedia?.apply {
            this@AVPlayer.start()
            playCallback?.playTime(duration)
            house.play = this
        }
    }



    override fun play(id: Int) {
        house.jumpToSong(id)
        preparedSong(house.currentSong())
    }

    private fun releaseCurrentMedia(){
        currentMedia?.release()
        house.play = null
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp?:return
        preparedSong(house.next())
    }


    override fun seekTo(time: Int) {
        currentMedia?.seekTo(time)
    }

    override fun next() {
        releaseCurrentMedia()
        preparedSong(house.currentSong())
    }

    override fun pre() {
        preparedSong(house.pre())
    }


    override fun pause() {
        currentMedia?.pause()
        playCallback?.pause(house.currentPlayId)
    }

    override fun stop() {
       currentMedia?.stop()
    }

}