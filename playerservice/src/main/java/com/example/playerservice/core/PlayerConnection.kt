package com.example.playerservice.core

import android.os.IBinder

class PlayerConnection : IPlayerService.Stub() {

    override fun songListID(songListId: IntArray?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rquestPlaySong(id: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestPlayList(): IntArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playTime(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLyric(): MutableList<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currentLyric(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}