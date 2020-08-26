package com.example.main.repository

import com.example.common.adapter.AVLiveData
import com.example.common.base.AndroidLifeCycleProvide
import com.example.common.base.IRepository
import com.example.main.http.DiscoveryApi
import com.example.main.http.entity.Block

interface IDiscoveryRepository :IRepository {
    fun blocks():AVLiveData<List<Block>>
}

class DiscoveryRepository(val api: DiscoveryApi, override val lifeCycleProvide: AndroidLifeCycleProvide) :IDiscoveryRepository{

    override fun blocks(): AVLiveData<List<Block>> {
        val blocks = AVLiveData<List<Block>>()
        api.blockPage().registerLifeCycle(lifeCycleProvide)
            .doOnComplete { blocks.value(it.data.blocks) }
            .doOnError { blocks.error(it) }
            .post()
        return blocks
    }
}