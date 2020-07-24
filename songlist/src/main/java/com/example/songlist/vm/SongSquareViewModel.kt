package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.viewModelFactory
import com.example.songlist.bean.Sub
import com.example.songlist.repository.ISongSquareRepository
import com.example.songlist.repository.SongSquareRepository
import com.example.songlist.utils.SafeMutableLiveData

class SongSquareViewModel(
    repository: ISongSquareRepository,
    override var lifeCycleProvide: AndroidLifeCycleProvide
) : AVViewModel<ISongSquareRepository>(repository) {
    var songCategorys = MutableLiveData<List<Sub>>()

    fun getSongCategoryList() {
        repository.getSongCategoryList()
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete { songCategorys.value = it.sub }
            .doOnError { songCategorys.value = null }
            .post()
    }

    companion object {
        fun factory(
            repository: ISongSquareRepository,
            lifeCycleProvide: AndroidLifeCycleProvide
        ) =
            viewModelFactory { SongSquareViewModel(repository, lifeCycleProvide) }
    }
}

class SongSquareViewModelFactory(
    val repository: SongSquareRepository,
    private val lifeCycleProvide: AndroidLifeCycleProvide
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongSquareViewModel(repository, lifeCycleProvide) as T
    }
}
