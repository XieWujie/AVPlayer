package com.example.main.viewmodel

import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.viewModelFactory
import com.example.main.repository.IMainRepository

class MainViewModel(repository:IMainRepository,
                    override var lifeCycleProvide: AndroidLifeCycleProvide
) :AVViewModel<IMainRepository>(repository){


    companion object{

        fun getFactory(repository: IMainRepository,lifeCycleProvide: AndroidLifeCycleProvide) = viewModelFactory { MainViewModel(repository,lifeCycleProvide) }
    }
}