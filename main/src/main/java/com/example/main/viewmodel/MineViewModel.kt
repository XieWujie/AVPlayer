package com.example.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.viewModelFactory
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.SubCountEntry
import com.example.main.repository.IMineIRepository


class MineViewModel internal constructor(repository: IMineIRepository,
                                         override var lifeCycleProvide: AndroidLifeCycleProvide
):AVViewModel<IMineIRepository>(repository){


    fun subCount():LiveData<SubCountEntry>{
        val subCountEntry = MutableLiveData<SubCountEntry>()
        repository.subCount().registerLifeCycle(lifeCycleProvide)
            .doOnComplete { subCountEntry.value = it }
            .doOnError { error.value = it }
            .post()
        return subCountEntry
    }

    fun played():LiveData<PlayRecordList>{
        val playedEntry = MutableLiveData<PlayRecordList>()
        repository.played().registerLifeCycle(lifeCycleProvide)
            .doOnComplete { playedEntry.value = it }
            .doOnError { error.value = it }
            .post()
        return playedEntry
    }


    companion object{
        fun factory(repository: IMineIRepository,lifeCycleProvide: AndroidLifeCycleProvide) = viewModelFactory { MineViewModel(repository,lifeCycleProvide) }
    }
}
