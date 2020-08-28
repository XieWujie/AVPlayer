package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.adapter.AVLiveData
import com.example.songlist.bean.HeightQualitySongListBean
import com.example.songlist.bean.Playlists
import com.example.songlist.bean.SongListBean
import com.example.songlist.fragment.SongListFragment
import com.example.songlist.repository.SongSquareFragmentRepository
import com.example.songlist.utils.SafeMutableLiveData
import com.xie.di.DiBus
import com.xie.di.ViewModelService

class SongListViewModel @ViewModelService(SongListFragment::class)constructor(
    val repository: SongSquareFragmentRepository
) :ViewModel() {
    val songList = SafeMutableLiveData<List<Playlists>>()
    private val heightQualitySongList = MutableLiveData<List<Playlists>>()
   private val  lifeCycleProvide = DiBus.lifeCycle<SongListFragment>()

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
