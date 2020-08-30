package com.example.main.di

import androidx.fragment.app.Fragment
import com.example.main.MainActivity
import com.example.main.adapter.mine.PageTabAdapter
import com.example.main.http.DiscoveryApi
import com.example.main.http.MainApi
import com.example.main.http.MineApi
import com.example.main.view.DiscoveryFragment
import com.example.main.view.MineFragment
import com.dibus.AutoWire
import com.dibus.DiBus
import com.dibus.Provide
import com.dibus.Service
import dibus.main.MainActivityCreator
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
        return PageTabAdapter(MainActivityCreator.get(),list)
    }

}