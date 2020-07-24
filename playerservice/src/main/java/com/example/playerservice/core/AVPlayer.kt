package com.example.playerservice.core

import android.content.Context
import android.media.MediaPlayer
import com.example.playerservice.IAVService

interface IAVPlayer{

    fun pause()
    fun stop()
    fun start()
    fun seekTo(time:Int)
    fun next()
    fun pre()
    fun prepareNext()
    fun play(id:Int)
    fun getHouse():SongHouse
    fun registerCallback(callback:IAVService)

}
class AVPlayer(val context: Context,private val house: SongHouse) : IAVPlayer,MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener {


    private var currentMedia:MediaPlayer? = null
    private var nextMedia:MediaPlayer? = null
    private var playCallback:IAVService? = null

    override fun start() {
        currentMedia?.start()
        house.invalidCurrentPlay = false
        playCallback?.apply {
            started(house.currentPlayId)
            pause(house.lastId)
        }
    }

    override fun registerCallback(callback: IAVService) {
        playCallback = callback
        house.registerCallback(callback)
    }


    override fun prepareNext() {
        val url = house.next().url
        nextMedia =  MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener(this@AVPlayer)
        }
    }



    override fun getHouse(): SongHouse {
        return house
    }

    override fun onPrepared(mp: MediaPlayer?) {
        val media = mp?:return
        if(currentMedia == null){
            currentMedia = media
            nextMedia = null
            start()
        }
        if(nextMedia == media){
            currentMedia?.setNextMediaPlayer(media)
        }
        house.play = currentMedia
    }



    override fun play(id: Int) {
        releaseCurrentMedia()
        house.jumpToSong(id)
        prepareNext()
    }

    private fun releaseCurrentMedia(){
        currentMedia?.apply {
            house.invalidCurrentPlay = true
            release()
        }
        currentMedia = null
    }

    override fun onCompletion(mp: MediaPlayer?) {
        val media = mp?:return
        if(media == currentMedia){
            releaseCurrentMedia()
            currentMedia == nextMedia
            nextMedia == null
            prepareNext()
        }
    }


    override fun seekTo(time: Int) {
        currentMedia?.seekTo(time)
    }

    override fun next() {
        releaseCurrentMedia()
        currentMedia = nextMedia
        start()
    }

    override fun pre() {
        releaseCurrentMedia()
    }


    override fun pause() {
        currentMedia?.pause()
    }

    override fun stop() {
       currentMedia?.stop()
    }

}