package com.example.songlist.repository

import android.util.Log
import com.example.common.adapter.AVLiveData
import com.example.songlist.bean.HeightQualitySongListBean
import com.example.songlist.bean.SongListBean
import com.example.songlist.fragment.SongListFragment
import com.example.songlist.http.SongSquareApi
import com.xie.di.AndroidLifeCycleProvide
import com.xie.di.DiBus
import com.xie.di.Service

class SongSquareFragmentRepository @Service constructor(
    private var api: SongSquareApi
) : ISongSquareFragmentRepository {

    private val lifeCycleProvide: AndroidLifeCycleProvide = DiBus.lifeCycle<SongListFragment>()

    override fun getHeightQualitySongList(
        before: Long,
        limit: Int,
        cat: String
    ): AVLiveData<HeightQualitySongListBean> {
        return api.getHeightQualitySongList(before, limit, cat)
            .registerLifeCycle(lifeCycleProvide)
            .doOnError(this::onHeightQualitySongListError)
            .doOnComplete(this::onHeightQualitySongListSuccess)
            .post()
    }

    private fun onHeightQualitySongListSuccess(bean: HeightQualitySongListBean) {
        Log.e(TAG, bean.toString())
    }

    private fun onHeightQualitySongListError(error: Throwable) {
        error.printStackTrace()
    }

    override fun getSongList(
        cat: String,
        offset: Int,
        limit: Int,
        order: String
    ): AVLiveData<SongListBean> {
        return api.getSongList(cat, offset, limit, order)
            .registerLifeCycle(lifeCycleProvide)
            .doOnError(this::onSongListError)
            .doOnComplete(this::onSongListSuccess)
            .post()
    }

    private fun onSongListSuccess(bean: SongListBean) {
        Log.e(TAG, bean.toString())
    }

    private fun onSongListError(error: Throwable) {
        error.printStackTrace()
    }


    private val TAG = this.javaClass.simpleName
}