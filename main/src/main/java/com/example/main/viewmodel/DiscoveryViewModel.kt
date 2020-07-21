package com.example.main.viewmodel

import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.viewModelFactory
import com.example.main.repository.DiscoveryRepository

class DiscoveryViewModel(repository: DiscoveryRepository,
                         override var lifeCycleProvide: AndroidLifeCycleProvide)
    :AVViewModel<DiscoveryRepository>(repository){



    companion object{
        fun getFactory(repository: DiscoveryRepository,lifeCycleProvide: AndroidLifeCycleProvide) =
            viewModelFactory { DiscoveryViewModel(repository,lifeCycleProvide) }
    }
}