package com.example.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dibus.*
import com.example.common.adapter.AVLiveData
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.Playlist
import com.example.main.http.entry.SubCountEntry
import com.example.main.repository.IMineIRepository
import com.example.main.view.MINE_FRAGMENT_SCOPE
import com.example.main.view.MineFragment


class MineViewModel @ViewModelService(MineFragment::class) internal constructor(private val repository: IMineIRepository
):ViewModel(){

    @Scope(MINE_FRAGMENT_SCOPE)
   lateinit var lifeCycleProvide:AndroidLifeCycleProvide

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
