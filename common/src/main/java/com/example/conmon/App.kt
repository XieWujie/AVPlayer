package com.example.conmon

import android.app.Application
import android.content.SharedPreferences
import com.example.route.AVRoute
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * 初始路由，本地信息，绑定网络模块
 */
open class App:Application(),KodeinAware{

//注入全局单例
    override val kodein: Kodein = Kodein.lazy {
        bind<App>() with singleton { this@App }
        import(androidXModule(this@App))
        import(httpClientModule)
    }
    private val preferences by instance<SharedPreferences>()

    override fun onCreate() {
        super.onCreate()
        AVRoute.init(this)//路由初始化，遍历app，查找相应类，耗时操作
        AccountAccessor.init(preferences)//注入本地信息
    }
}