package com.example.main.repository

import com.dibus.Service
import com.dibus.diBus
import com.example.common.adapter.AVLiveData
import com.example.common.base.IRepository
import com.example.main.http.DiscoveryApi
import com.example.main.http.entity.Block
import com.example.main.view.DiscoveryFragment

interface IDiscoveryRepository :IRepository {
    fun blocks():AVLiveData<List<Block>>
}

class DiscoveryRepository @Service constructor(private val api: DiscoveryApi) :IDiscoveryRepository{

    override fun blocks(): AVLiveData<List<Block>> {
        val blocks = AVLiveData<List<Block>>()
        api.blockPage()
            .doOnComplete { blocks.value(it.data.blocks) }
            .doOnError { blocks.error(it) }
            .post(diBus.scope(DiscoveryFragment::class))
        return blocks
    }
}