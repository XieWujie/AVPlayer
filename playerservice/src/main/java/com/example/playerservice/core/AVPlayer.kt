package com.example.playerservice

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.example.playerservice.http.Song
import java.util.*
import java.util.concurrent.SynchronousQueue

interface IAVPlayer{

    fun pause()
    fun stop()
    fun play(id:Int)
    fun seekTo(time:Int)
    fun next()
    fun pre()

    fun haveLoadSong(id:Int):Boolean

}
class AVPlayer(val context: Context) :IAVPlayer{
    override fun haveLoadSong(id: Int):Boolean {

    }

    val prepareQueue = SynchronousQueue<MediaPlayer>()
    val waitQueue = LinkedList<Song>()
    private var currentMedia:MediaPlayer? = null


    override fun play(id:Int) {
        releaseCurrentMedia()
        currentMedia =
        val player = MediaPlayer.create(context,Uri.parse(url))
    }

    fun releaseCurrentMedia(){
        currentMedia?.apply {
            release()
        }
        currentMedia = null
    }


    override fun seekTo(time: Int) {

    }

    override fun next() {

    }

    override fun pre() {

    }

    private val player:MediaPlayer = MediaPlayer()

    override fun pause() {

    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }






}