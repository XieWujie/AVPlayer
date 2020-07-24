package com.example.conmon.playservice

import com.example.playerservice.IAVService
import java.util.*
import kotlin.collections.ArrayList

class ResponseConnection private constructor():IAVService.Stub(){

    override fun pause(id: Int) {
        for(i in pauseObservers.size-1 downTo 0){
            pauseObservers[i].invoke(id)
        }
    }

    override fun started(id: Int) {
        for(i in startedObservers.size-1 downTo 0){
            startedObservers[i].invoke(id)
        }
    }


    override fun playedTime(time: Int) {

    }

    override fun lyricChange(time: Int) {
        for(i in lyricChanageObservers.size-1 downTo 0){
            lyricChanageObservers[i].invoke(time)
        }
    }

    override fun lyric(lyric: MutableMap<Any?, Any?>?) {
      for(observer in lyrics){
          val l  = lyric as Map<Int,String>
          observer.invoke(l.toSortedMap() as TreeMap<Int, String>)
      }
    }

    override fun playList(ids: IntArray?) {

    }

    companion object{
        private val lyrics = ArrayList<((lyric:TreeMap<Int,String>)->Unit)>()
        private val pauseObservers = ArrayList<((id:Int)->Unit)>()
        private val startedObservers = ArrayList<((id:Int)->Unit)>()
        private val lyricChanageObservers = ArrayList<((time:Int)->Unit)>()
        private val INSTANCE = ResponseConnection()
        operator fun invoke() = INSTANCE

        fun registerPauseEvent(observer: (id:Int)->Unit){
            pauseObservers.add(observer)
        }
        fun registerStartedEvent(observer: (id:Int)->Unit){
            startedObservers.add(observer)
        }

        fun registerLyricEvent(observer: (lyric:TreeMap<Int,String>) -> Unit){
            lyrics.add(observer)
        }

        fun registerLyricChangeEvent(observer: (time: Int) -> Unit){
            lyricChanageObservers.add(observer)
        }
    }
}
