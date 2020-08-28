package com.example.main.di

import androidx.fragment.app.Fragment
import com.example.main.MainActivity
import com.example.main.adapter.mine.PageTabAdapter
import com.example.main.http.DiscoveryApi
import com.example.main.http.MainApi
import com.example.main.http.MineApi
import com.example.main.view.DiscoveryFragment
import com.example.main.view.MineFragment
import com.xie.di.AutoWire
import com.xie.di.DiBus
import com.xie.di.Provide
import com.xie.di.Service
import retrofit2.Retrofit

@Service
class MainDi {

    @AutoWire
    lateinit var retrofit:Retrofit

    @Provide
   fun provideMineAPi(): MineApi = retrofit.create(MineApi::class.java)

    @Provide
    fun provideDiscoveryApi(): DiscoveryApi = retrofit.create(DiscoveryApi::class.java)

    @Provide
    fun provideMainApi(): MainApi = retrofit.create(MainApi::class.java)

    @Provide
    fun providePageTabAdapter():PageTabAdapter{
        val list = arrayListOf<Fragment>(MineFragment(),DiscoveryFragment())
        return PageTabAdapter(DiBus.load<MainActivity>(),list)
    }

}