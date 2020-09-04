package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dibus.AndroidLifeCycleProvide
import com.example.songlist.SongSquareActivity
import com.example.songlist.bean.Sub
import com.example.songlist.repository.ISongSquareRepository
import com.dibus.DiBus
import com.dibus.ViewModelService
import com.dibus.scope

class SongSquareViewModel @ViewModelService(SongSquareActivity::class) constructor(
    val repository: ISongSquareRepository):ViewModel() {
    var songCategorys = MutableLiveData<List<Sub>>()


    fun getSongCategoryList() {
        repository.getSongCategoryList()
            .doOnComplete { songCategorys.value = it.sub }
            .doOnError { songCategorys.value = null }
            .post(scope(SongSquareActivity::class))
    }


}

