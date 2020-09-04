package com.example.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.common.adapter.AVLiveData
import com.example.main.http.entity.Block
import com.example.main.repository.IDiscoveryRepository
import com.example.main.view.DiscoveryFragment
import com.dibus.AndroidLifeCycleProvide
import com.dibus.DiBus
import com.dibus.ViewModelService
import com.dibus.scope
import dibus.main.DiscoveryFragmentCreator

class DiscoveryViewModel @ViewModelService(DiscoveryFragment::class) constructor(val repository: IDiscoveryRepository):ViewModel() {



    fun blocks():AVLiveData<List<Block>>{
        val newBlocks = AVLiveData<List<Block>>()
        repository.blocks()
            .doOnComplete { it ->
                val new = it.filter {
                    it.showType =="HOMEPAGE_SLIDE_PLAYLIST" ||
                            it.showType =="HOMEPAGE_SLIDE_SONGLIST_ALIGN"
                }
                newBlocks.value(new)
            }
            .doOnError { newBlocks.error(it) }
            .post(scope(DiscoveryFragment::class))
        return newBlocks
    }

}