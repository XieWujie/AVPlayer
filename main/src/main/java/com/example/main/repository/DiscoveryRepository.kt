package com.example.main.repository

import com.dibus.Service
import com.example.common.adapter.AVLiveData
import com.example.common.base.IRepository
import com.example.main.http.DiscoveryApi
import com.example.main.http.entity.Block

interface IDiscoveryRepository :IRepository {
    fun blocks():AVLiveData<List<Block>>
}

class DiscoveryRepository @Service constructor(private val api: DiscoveryApi) :IDiscoveryRepository{

    override fun blocks(): AVLiveData<List<Block>> {
        val blocks = AVLiveData<List<Block>>()
        api.blockPage()
            .doOnComplete { blocks.value(it.data.blocks) }
            .doOnError { blocks.error(it) }
            .post()
        return blocks
    }
}