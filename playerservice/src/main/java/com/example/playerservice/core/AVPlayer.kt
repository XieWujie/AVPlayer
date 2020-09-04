
package com.example.playerservice.core


import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.dibus.Service
import com.example.common.IAVService
import com.example.common.extension.toast
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
    fun registerCallback(callback: IAVService)

}
class AVPlayer @Service constructor(val context: Context,private val house: SongHouse) : IAVPlayer,MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener {


    private var currentMedia:MediaPlayer? = null
    private var playCallback:IAVService? = null

    override fun start() {
        currentMedia?.start()
        playCallback?.started(house.currentPlayId)
        Log.d(TAG,"start")
    }

    override fun registerCallback(callback: IAVService) {
        playCallback = callback
        house.registerCallback(callback)
    }


     private fun preparedSong(song:Song) {
         releaseCurrentMedia()
        val url = song.url
         if(null == url){
             context.toast("找不到歌曲URL")
             return
         }
         MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener(this@AVPlayer)
             setOnCompletionListener(this@AVPlayer)
        }
    }



    override fun getHouse(): SongHouse {
        return house
    }

    override fun onPrepared(mp: MediaPlayer?) {
        currentMedia = mp?:return
        Log.d(TAG,"歌曲已经准备完成 ${house.currentPlayId}")
        currentMedia?.apply {
            this@AVPlayer.start()
            playCallback?.playTime(duration)
            house.play = this
        }
    }



    override fun play(id: Int) {
        house.jumpToSong(id)
        preparedSong(house.currentSong())
        Log.d(TAG,"开始准备播放$id")
    }

    private fun releaseCurrentMedia(){
        currentMedia?.release()
        house.play = null
        Log.d(TAG,"release $currentMedia")
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp?:return
        preparedSong(house.next())
        Log.d(TAG,"complete")
    }


    override fun seekTo(time: Int) {
        currentMedia?.seekTo(time)
        Log.d(TAG,"seekTo$time")
    }

    override fun next() {
        releaseCurrentMedia()
        preparedSong(house.currentSong())
        Log.d(TAG,"next()")
    }

    override fun pre() {
        preparedSong(house.pre())
        Log.d(TAG,"pre()")
    }


    override fun pause() {
        currentMedia?.pause()
        playCallback?.pause(house.currentPlayId)
        Log.d(TAG,"pause()")
    }

    override fun stop() {
       currentMedia?.stop()
        Log.d(TAG,"stop()")
    }

    companion object{
        private const val TAG = "AVPlayer"
    }
}