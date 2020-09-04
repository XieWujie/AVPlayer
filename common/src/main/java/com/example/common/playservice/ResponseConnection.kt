package com.example.common.playservice

import com.example.common.IAVService
import com.example.common.entity.SongDetail
import com.dibus.DiBus
import java.util.*
import kotlin.collections.HashSet

class ResponseConnection private constructor(): IAVService.Stub(){

    private val diBus = DiBus()

    override fun currentSongDetail(songDetail: SongDetail?) {

    }

    override fun pause(id: Int) {
        diBus.sendEvent(PlayEvent.Pause(id))

    }



    override fun started(id: Int) {
        diBus.sendStickEvent(PlayEvent.Started(id))

    }

    override fun playTime(time: Int) {
        diBus.sendStickEvent(PlayEvent.PlayTime(time))

    }

    private val playTime =  PlayEvent.PlayTime(0)
    override fun playedTime(time: Int) {
        diBus.sendEvent(playTime.copy(time = time))
    }


    override fun lyricChange(time: Int) {
        diBus.sendEvent(PlayEvent.LyricChange(time))
    }

    @Suppress("UNCHECKED_CAST")
    override fun lyric(lyric: MutableMap<Any?, Any?>?) {
        diBus.sendStickEvent(PlayEvent.Lyric((lyric as MutableMap<Int, String>).toSortedMap()))
    }

    override fun playList(ids: IntArray?) {

    }



    companion object{

        private val iConnectionCallbacks = HashSet<IConnectionCallback>()
        private val INSTANCE = ResponseConnection()
        operator fun invoke() = INSTANCE

    }
}

interface IConnectionCallback{
    fun pause(id: Int){

    }
    fun started(id: Int){}

    fun playedTime(time: Int){}

    fun lyricChange(time: Int){}

    fun lyric(lyric: SortedMap<Int, String>){}

    fun playTime(time: Int) {}
}
