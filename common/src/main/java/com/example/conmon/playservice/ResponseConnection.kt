package com.example.conmon.playservice

import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.playerservice.IAVService
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class ResponseConnection private constructor():IAVService.Stub(){

    override fun pause(id: Int) {
        iConnectionCallbacks.forEach {
            it.pause(id)
        }
    }

    override fun started(id: Int) {
        iConnectionCallbacks.forEach {
            it.started(id)
        }
    }

    override fun playTime(time: Int) {
        iConnectionCallbacks.forEach {
            it.playTime(time)
        }
    }


    override fun playedTime(time: Int) {
        iConnectionCallbacks.forEach {
            it.playedTime(time)
        }
    }

    override fun lyricChange(time: Int) {
        iConnectionCallbacks.forEach {
            it.lyricChange(time)
        }
    }

    override fun lyric(lyric: MutableMap<Any?, Any?>?) {
        iConnectionCallbacks.forEach {
            it.lyric((lyric as MutableMap<Int, String>).toSortedMap())
        }
    }

    override fun playList(ids: IntArray?) {

    }



    companion object{

        private val iConnectionCallbacks = HashSet<IConnectionCallback>()
        private val INSTANCE = ResponseConnection()
        operator fun invoke() = INSTANCE

        fun registerObservable(observable:IConnectionCallback,lifeCycleProvide: AndroidLifeCycleProvide){
            iConnectionCallbacks.add(observable)

            lifeCycleProvide.provide {
                iConnectionCallbacks.remove(observable)
            }
        }

        fun removeObservable(observable: IConnectionCallback){
            iConnectionCallbacks.remove(observable)
        }

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
