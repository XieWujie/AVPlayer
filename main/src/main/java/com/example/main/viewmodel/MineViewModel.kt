package com.example.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.adapter.AVLiveData
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.Playlist
import com.example.main.http.entry.SubCountEntry
import com.example.main.repository.IMineIRepository
import com.example.main.view.MineFragment
import com.xie.di.DiBus
import com.xie.di.ViewModelService


class MineViewModel @ViewModelService(MineFragment::class) internal constructor(private val repository: IMineIRepository
):ViewModel(){

    private val lifeCycleProvide = DiBus.lifeCycle<MineFragment>()

    fun subCount():LiveData<SubCountEntry>{
        val subCountEntry = MutableLiveData<SubCountEntry>()
        repository.subCount().registerLifeCycle(lifeCycleProvide)
            .doOnComplete { subCountEntry.value = it }
            .post()
        return subCountEntry
    }

    fun played():LiveData<PlayRecordList>{
        val playedEntry = MutableLiveData<PlayRecordList>()
        repository.played().registerLifeCycle(lifeCycleProvide)
            .doOnComplete { playedEntry.value = it }
            .post()
        return playedEntry
    }

    fun playedList():AVLiveData<List<Playlist>>{
        return repository.playList()
    }


}
