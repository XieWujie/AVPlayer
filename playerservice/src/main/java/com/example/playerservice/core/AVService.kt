package com.example.playerservice.core

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.common.IAVService
import com.example.common.PLAY_CALLBACK_SERVICE_ACTION
import com.example.playerservice.util.ServiceInject


private const val ACTION_LOAd_SONGS = "com.example.playerservice.core.action.load.songs"
private const val ACTION_LOAD_LYRIC = "com.example.playerservice.core.action.load.lyric"
private const val ACTION_PREPARE_SONG ="com.example.playerservice.core.action.prepare.song"


private const val IDS = "com.example.playerservice.core.IDS"
private const val LYRIC = "com.example.playerservice.core.LYRIC"


class AVService : Service(){

    private var callbackConnection:IAVService? = null
    private var connection:PlayerConnection? = null
    override fun onBind(intent: Intent?): IBinder? {
        connection = ServiceInject.inject(applicationContext)
        bindCallback()
        return connection
    }

    private fun bindCallback(){
        val intent = Intent(PLAY_CALLBACK_SERVICE_ACTION)
        intent.`package` = application.packageName
        this.bindService(intent,connect,Context.BIND_AUTO_CREATE)
    }

    private val connect = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            callbackConnection =IAVService.Stub.asInterface(service)
            connection?.registerCallback(callbackConnection!!)
        }

    }
}
