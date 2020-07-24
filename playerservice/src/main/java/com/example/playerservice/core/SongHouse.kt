package com.example.playerservice.core


import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.util.SparseArray
import androidx.core.util.containsKey
import com.example.playerservice.IAVService
import com.example.playerservice.http.Song
import com.example.playerservice.repository.ISongRepository
import java.util.*
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class SongHouse(val repository: ISongRepository) : Handler(Looper.myLooper()) ,Runnable{

    private val songs = SparseArray<Song>()
    private var playCallback: IAVService? = null
    private val executors = Executors.newCachedThreadPool()
    var currentSongIndex: Int = 0
        set(value) {
            field = value % songs.size()
        }
    var currentPlayId = 0
        private set
    @SuppressLint("UseSparseArrays")
    val lyric = TreeMap<Int, String>()
    var invalidCurrentPlay = false
    private val scheduleExecutor = Executors.newScheduledThreadPool(1)
    var lastId = 0
        private set
    var play: MediaPlayer? = null
        set(value) {
            field = value
            executors.execute {
                Log.d("SongHouse", "Init barrier wait")
                lyricBarrier.await()
                Log.d("SongHouse", "barrier down")
            }
        }

    private var lyricTree: List<Int>? = null
    private var lyricPosition = 0
    private val lyricBarrier = CyclicBarrier(2)


    fun requestId(id: Int, ids: String?, completeCallback: () -> Unit) {
        if (!songs.containsKey(key = id)) {
            val s = ids ?: id.toString()
            repository.request(s, songs) {
                completeCallback()
            }
        } else {
            completeCallback()
        }
    }

    /**
     * 时间调度
     */
    override fun run() {
        val mediaPlay = play ?: return
        val lyrics = lyricTree ?: return
        val position = mediaPlay.currentPosition
        if(lyricPosition>lyrics.size){
            return
        }
        val currentTime = lyrics[lyricPosition]
        if (position >currentTime-LYRIC_BEFORE_TIME) {
            val message = Message.obtain()
            message.what = LYRIC_ITEM_WHAT
            message.arg1 = lyrics[lyricPosition]
            Log.d("position","media ${position} lyric${lyrics[lyricPosition]} item$lyricPosition")
            sendMessage(message)
            ++lyricPosition
        }

    }


    fun registerCallback(callback: IAVService) {
        playCallback = callback
    }


    fun currentSong(): Song {
        return songs.valueAt(currentSongIndex)
    }

    /**
     * 不做安全检查，保证songs中存在这个id
     * @param id 歌曲的id
     */
    fun jumpToSong(id: Int) {
        lastId = currentPlayId
        currentSongIndex = songs.indexOfKey(id)
        currentPlayId = id
        invalidCurrentPlay = false
    }

    fun next(): Song {
        val song = if (invalidCurrentPlay) {
            lastId = currentSongIndex
            ++currentSongIndex
            val song = songs.valueAt(currentSongIndex)
            currentPlayId = song.id
            song
        } else {
            songs.valueAt(currentSongIndex)
        }
        lyricBarrier.reset()
        loadLyric(currentPlayId)
        return song
    }

    fun pre(): Song {
        --currentSongIndex
        val song = songs.valueAt(currentSongIndex)
        currentPlayId = song.id
        // loadLyric(song.id)
        return song
    }


    private fun loadLyric(id: Int) {
        repository.getLyric(id, lyric) {
            playCallback?.lyric(lyric)
            executors.execute {
                Log.d("SongHouse", "Load barrier wait")
                lyricBarrier.await()
                Log.d("SongHouse", "barrier down")
                lyricTree = lyric.keys.toList()
                lyricPosition = 0
                lyricSchedule()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun lyricSchedule() {
         play ?: return
        lyricTree ?: return
        lyricPosition = 0
        scheduleExecutor.scheduleWithFixedDelay(this, 0, 300, TimeUnit.MILLISECONDS)
    }



    override fun handleMessage(msg: Message) {
        when (msg.what) {
            LYRIC_ITEM_WHAT -> {
                playCallback?.lyricChange(msg.arg1)
            }
        }
    }


    companion object {
        const val LYRIC_BEFORE_TIME = 500
        const val LYRIC_ITEM_WHAT = 1
    }
}