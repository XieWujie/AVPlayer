package com.example.main.viewmodel

import com.example.common.base.AVViewModel
import com.example.common.base.AndroidLifeCycleProvide
import com.example.common.extension.viewModelFactory
import com.example.main.repository.IMainRepository

class MainViewModel(repository:IMainRepository,
                    override var lifeCycleProvide: AndroidLifeCycleProvide
) :AVViewModel<IMainRepository>(repository){


    companion object{

        fun getFactory(repository: IMainRepository,lifeCycleProvide: AndroidLifeCycleProvide) = viewModelFactory { MainViewModel(repository,lifeCycleProvide) }
    }
}