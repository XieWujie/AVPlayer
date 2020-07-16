package com.example.conmon

import android.app.Application
import com.example.route.AVRoute
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

open class App:Application(),KodeinAware{

    override val kodein: Kodein = Kodein.lazy {
        bind<App>() with singleton { this@App }
        import(androidXModule(this@App))
        import(httpClientModule)
    }

    override fun onCreate() {
        super.onCreate()
        AVRoute.init(this)
    }
}