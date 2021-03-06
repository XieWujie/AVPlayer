package com.example.common.playservice

import java.util.*

sealed class PlayEvent {

    data class Pause(val id:Int) : PlayEvent()
    data class Started(val id: Int):PlayEvent()
    data class PlayedTime(val time: Int):PlayEvent()
    data class LyricChange(val time: Int):PlayEvent()
    data class Lyric(val lyric: SortedMap<Int, String>):PlayEvent()
    data class PlayTime(val time: Int):PlayEvent()

}



fun lyric(lyric: SortedMap<Int, String>){}

