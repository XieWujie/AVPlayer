package com.example.main.viewmodel

import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.viewModelFactory
import com.example.main.http.entity.Block
import com.example.main.repository.DiscoveryRepository
import com.example.main.repository.IDiscoveryRepository

class DiscoveryViewModel(repository: IDiscoveryRepository,
                         override var lifeCycleProvide: AndroidLifeCycleProvide)
    :AVViewModel<IDiscoveryRepository>(repository){


    fun blocks():AVLiveData<List<Block>>{
        val newBlocks = AVLiveData<List<Block>>()
        repository.blocks()
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete {
                val new = it.filter {
                    it.showType =="HOMEPAGE_SLIDE_PLAYLIST" ||
                            it.showType =="HOMEPAGE_SLIDE_SONGLIST_ALIGN"
                }
                newBlocks.value(new)
            }
            .doOnError { newBlocks.error(it) }
            .post()
        return newBlocks
    }

    companion object{
        fun getFactory(repository: IDiscoveryRepository,lifeCycleProvide: AndroidLifeCycleProvide) =
            viewModelFactory { DiscoveryViewModel(repository,lifeCycleProvide) }
    }
}