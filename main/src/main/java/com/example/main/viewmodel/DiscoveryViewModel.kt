package com.example.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.common.adapter.AVLiveData
import com.example.main.http.entity.Block
import com.example.main.repository.IDiscoveryRepository
import com.example.main.view.DiscoveryFragment
import com.xie.di.AndroidLifeCycleProvide
import com.xie.di.DiBus
import com.xie.di.ViewModelService

class DiscoveryViewModel @ViewModelService(DiscoveryFragment::class) constructor(val repository: IDiscoveryRepository):ViewModel() {


    private val  lifeCycleProvide:AndroidLifeCycleProvide = DiBus.lifeCycle<DiscoveryFragment>()

    fun blocks():AVLiveData<List<Block>>{
        val newBlocks = AVLiveData<List<Block>>()
        repository.blocks()
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete { it ->
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

}