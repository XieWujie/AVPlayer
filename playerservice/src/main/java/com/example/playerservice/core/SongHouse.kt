package com.example.playerservice.core


import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.util.SparseArray
import androidx.core.util.containsKey
import com.example.conmon.extension.toast
import com.example.playerservice.IAVService
import com.example.playerservice.http.Song
import com.example.playerservice.repository.ISongRepository
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class SongHouse(val repository: ISongRepository, val context: Context) : Handler(Looper.myLooper()),
    Runnable {

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
    private val scheduleExecutor = Executors.newScheduledThreadPool(1)
    @Volatile
    var play: MediaPlayer? = null

    @Volatile
    private var lyricTree: List<Int>? = null
    private var lyricPosition = 0


    fun requestId(id: Int, ids: String?, completeCallback: () -> Unit) {
        if(songs.containsKey(id)){
            completeCallback()
            return
        }
        val s = ids ?: id.toString()
        repository.request(s, songs) {
            if (it != null) {
                context.toast(it.message ?: "歌曲请求错误")
            } else {
                completeCallback()
            }
        }
    }


    var oneSecond = 0
    /**
     * 时间调度
     */
    override fun run() {
        val mediaPlay = play ?: return
        val position = mediaPlay.currentPosition
        if (oneSecond and 3 == 0) {
            val message = Message.obtain()
            message.what = ONE_SECOND_EVENT
            message.arg1 = position
            sendMessage(message)
        }
        Log.d(TAG, "current position $position")
        ++oneSecond
        val lyrics = lyricTree ?: return
        if (lyricPosition > lyrics.size) {
            return
        }
        val currentTime = lyrics[lyricPosition]
        if (position > currentTime - LYRIC_BEFORE_TIME) {
            val message = Message.obtain()
            message.what = LYRIC_ITEM_WHAT
            message.arg1 = lyrics[lyricPosition]
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
    fun jumpToSong(id: Int,isLoadPosition:Boolean = true) :Song{
        if(isLoadPosition){
            currentSongIndex = songs.indexOfKey(id)
            currentPlayId = id
        }
        loadLyric(id)
        return songs.valueAt(currentSongIndex)
    }

    fun next(): Song {
       currentPlayId = songs.keyAt(++currentSongIndex)
        return jumpToSong(currentPlayId,false)
    }

    fun pre(): Song {
        currentPlayId = songs.keyAt(--currentSongIndex)
        return jumpToSong(currentPlayId,false)
    }



    private fun loadLyric(id: Int) {
        repository.getLyric(id, lyric) {
            if (it != null) {
                context.toast(it.message ?: "歌词请求错误")
            } else {
                playCallback?.lyric(lyric)
                executors.execute {
                    lyricTree = lyric.keys.toList()
                    lyricPosition = 0
                    lyricSchedule()
                }
            }
        }
    }

    var isScheduleStart = false
    @SuppressLint("NewApi")
    private fun lyricSchedule() {
        lyricPosition = 0
        if (!isScheduleStart) {
            scheduleExecutor.scheduleWithFixedDelay(this, 0, 250, TimeUnit.MILLISECONDS)
            isScheduleStart = true
        }
    }


    override fun handleMessage(msg: Message) {
        when (msg.what) {
            LYRIC_ITEM_WHAT -> {
                playCallback?.lyricChange(msg.arg1)
            }
            ONE_SECOND_EVENT -> {
                playCallback?.playedTime(msg.arg1)
            }
        }
    }


    companion object {
        const val LYRIC_BEFORE_TIME = 260
        const val LYRIC_ITEM_WHAT = 1
        const val ONE_SECOND_EVENT = 2

        private const val TAG = "SongHouse"
    }
}