package com.example.common.playservice

import android.app.Application
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.common.IPlayerService
import com.example.common.PLAY_CONNECTION_ACTION

class PlayerService : Service() {

    var responseConnection:ResponseConnection? = null
    override fun onBind(intent: Intent): IBinder {
        responseConnection = ResponseConnection()
        return responseConnection!!
    }

    companion object{

        var playConnection: IPlayerService? = null
        private set
        fun bindPlayService(application: Application){
            val intent = Intent(PLAY_CONNECTION_ACTION)
            intent.`package` = application.packageName
            application.bindService(intent, connection,Context.BIND_AUTO_CREATE)
        }

        private val connection = object :ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                try {
                    playConnection = IPlayerService.Stub.asInterface(service)
                }catch (e:Throwable){
                    e.printStackTrace()
                }
            }

        }
    }
}
