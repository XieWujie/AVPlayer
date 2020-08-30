package com.example.avplayer

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.common.AccountAccessor
import com.example.common.database.AppDbInstance
import com.example.common.playservice.PlayerService
import com.example.route.AVRoute
import com.dibus.DiBus
import com.example.common.HttpDiService
import dibus.common.HttpDiServiceCreator

/**
 * 初始路由，本地信息，绑定网络模块
 */
open class App:Application(){


    override fun onCreate() {
        super.onCreate()
        app = this
        Class.forName(HttpDiServiceCreator::class.java.canonicalName!!)
        DiBus.register(this)
        val sharedPreferences =getSharedPreferences("av",Context.MODE_PRIVATE)
        AVRoute.init(this)
        AccountAccessor.init(sharedPreferences)
        DiBus.register(SharedPreferences::class.java.canonicalName!!,sharedPreferences)//注入本地信息
        PlayerService.bindPlayService(this)//启动后台服务
        DiBus.register(this)
        AppDbInstance.registerAppDb(AppDatabase.getInstance(this))
    }

    companion object{
        lateinit var app: App
        fun get() = app
    }

}

