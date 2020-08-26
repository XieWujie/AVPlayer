package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.songlist.SongSquareActivity
import com.example.songlist.bean.Sub
import com.example.songlist.repository.ISongSquareRepository
import com.xie.di.DiBus
import com.xie.di.ViewModelService

class SongSquareViewModel @ViewModelService(SongSquareActivity::class) constructor(
    val repository: ISongSquareRepository):ViewModel() {
    var songCategorys = MutableLiveData<List<Sub>>()

    val lifeCycleProvide = DiBus.lifeCycle<SongSquareActivity>()

    fun getSongCategoryList() {
        repository.getSongCategoryList()
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete { songCategorys.value = it.sub }
            .doOnError { songCategorys.value = null }
            .post()
    }


}

