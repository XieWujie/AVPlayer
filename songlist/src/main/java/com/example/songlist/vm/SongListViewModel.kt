package com.example.songlist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dibus.AndroidLifeCycleProvide
import com.example.common.adapter.AVLiveData
import com.example.songlist.bean.HeightQualitySongListBean
import com.example.songlist.bean.Playlists
import com.example.songlist.bean.SongListBean
import com.example.songlist.fragment.SongListFragment
import com.example.songlist.repository.SongSquareFragmentRepository
import com.example.songlist.utils.SafeMutableLiveData
import com.dibus.DiBus
import com.dibus.ViewModelService
import com.dibus.scope

class SongListViewModel @ViewModelService(SongListFragment::class)constructor(
    val repository: SongSquareFragmentRepository
) :ViewModel() {
    val songList = SafeMutableLiveData<List<Playlists>>()
    private val heightQualitySongList = MutableLiveData<List<Playlists>>()

    fun getHeightQualitySongList(
        before: Long = 0,
        limit: Int = 18,
        cat: String = "全部"
    ): AVLiveData<HeightQualitySongListBean> {
        return repository
            .getHeightQualitySongList(before, limit, cat)
            .doOnComplete { heightQualitySongList.value = it.playlists }
            .doOnError { heightQualitySongList.value = null }
            .post(scope(SongListFragment::class))
    }

    fun getSongList(
        cat: String = "全部",
        offset: Int = 0,
        limit: Int = 18,
        order: String = "hot"
    ): AVLiveData<SongListBean> {
        return repository
            .getSongList(cat, offset, limit, order)
            .doOnComplete { songList.value = it.playlists }
            .doOnError { songList.value = null }
            .post(scope(SongListFragment::class))
    }

}
