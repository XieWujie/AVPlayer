package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dibus.AndroidLifeCycleProvide
import com.example.songlist.SongSquareActivity
import com.example.songlist.bean.Sub
import com.example.songlist.repository.ISongSquareRepository
import com.dibus.DiBus
import com.dibus.ViewModelService

class SongSquareViewModel @ViewModelService(SongSquareActivity::class) constructor(
    val repository: ISongSquareRepository):ViewModel() {
    var songCategorys = MutableLiveData<List<Sub>>()

    private val lifeCycleProvide = AndroidLifeCycleProvide(DiBus.load<SongSquareActivity>())

    fun getSongCategoryList() {
        repository.getSongCategoryList()
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete { songCategorys.value = it.sub }
            .doOnError { songCategorys.value = null }
            .post()
    }


}

