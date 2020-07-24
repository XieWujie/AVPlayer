package com.example.playerservice.core

import android.content.Context
import com.example.playerservice.IAVService
import com.example.playerservice.IPlayerService

class PlayerConnection(val context: Context, val player: IAVPlayer) : IPlayerService.Stub(){

    val house = player.getHouse()
    override fun rquestPlaySong(id: Int,ids:String?) {
       house.requestId(id,ids){
           player.play(id)
       }
    }

    fun registerCallback(callback:IAVService){
        player.registerCallback(callback)
    }
    override fun pause() {
        player.pause()
    }

    override fun start() {
        player.start()
    }

    override fun seek(time: Int) {
        player.seekTo(time)
    }

}