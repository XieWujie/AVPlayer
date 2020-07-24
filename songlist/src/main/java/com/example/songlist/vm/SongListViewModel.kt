package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.songlist.bean.HeightQualitySongListBean
import com.example.songlist.bean.Playlists
import com.example.songlist.bean.SongListBean
import com.example.songlist.bean.Sub
import com.example.songlist.repository.SongSquareFragmentRepository
import com.example.songlist.utils.SafeMutableLiveData

class SongListViewModel(
    repository: SongSquareFragmentRepository,
    override var lifeCycleProvide: AndroidLifeCycleProvide
) : AVViewModel<SongSquareFragmentRepository>(repository) {
    var songList = SafeMutableLiveData<List<Playlists>>()
    var heightQualitySongList = MutableLiveData<List<Playlists>>()


    fun getHeightQualitySongList(
        before: Long = 0,
        limit: Int = 18,
        cat: String = "全部"
    ): AVLiveData<HeightQualitySongListBean> {
        return repository
            .getHeightQualitySongList(before, limit, cat)
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete { heightQualitySongList.value = it.playlists }
            .doOnError { heightQualitySongList.value = null }
            .post()
    }

    fun getSongList(
        cat: String = "全部",
        offset: Int = 0,
        limit: Int = 18,
        order: String = "hot"
    ): AVLiveData<SongListBean> {
        return repository
            .getSongList(cat, offset, limit, order)
            .registerLifeCycle(lifeCycleProvide)
            .doOnComplete { songList.value = it.playlists }
            .doOnError { songList.value = null }
            .post()
    }

}

class SongListViewModelFactory(
    val repository: SongSquareFragmentRepository,
    private val lifeCycleProvide: AndroidLifeCycleProvide
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongListViewModel(repository, lifeCycleProvide) as T
    }
}