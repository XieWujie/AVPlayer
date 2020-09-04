package com.example.avplayer

import android.app.Application
import com.dibus.DiBus
import com.dibus.diBus
import com.example.common.AccountAccessor
import com.example.common.database.AppDbInstance
import com.example.common.playservice.PlayerService
import com.example.route.AVRoute
import dibus.common.HttpDiServiceCreator

/**
 * 初始路由，本地信息，绑定网络模块
 */
open class App:Application(){

    override fun onCreate() {
        super.onCreate()
        HttpDiServiceCreator.get()
        diBus.injectApplication(this)
        AVRoute.init(this)
        AccountAccessor.init(DiBus.load())
        PlayerService.bindPlayService(this)//启动后台服务
        AppDbInstance.registerAppDb(AppDatabase.getInstance(this))
    }

}

