package com.example.main.http

import com.example.conmon.adapter.AVLiveData
import com.example.main.http.entity.DiscoveryEntity
import retrofit2.http.GET

interface DiscoveryApi{

    @GET("/homepage/block/page")
    fun blockPage():AVLiveData<DiscoveryEntity>
}