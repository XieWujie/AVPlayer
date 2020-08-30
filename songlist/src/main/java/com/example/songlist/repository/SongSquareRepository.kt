package com.example.songlist.repository

import android.util.Log
import com.example.common.adapter.AVLiveData
import com.example.songlist.SongSquareActivity
import com.example.songlist.bean.IBean
import com.example.songlist.bean.SongCategoryBean
import com.example.songlist.http.SongSquareApi
import com.dibus.AndroidLifeCycleProvide
import com.dibus.DiBus
import com.dibus.Service

class SongSquareRepository @Service constructor(
    private val api: SongSquareApi

) : ISongSquareRepository {
    private val lifeCycleProvide: AndroidLifeCycleProvide = DiBus.lifeCycle<SongSquareActivity>()
    override fun getSongCategoryList(): AVLiveData<SongCategoryBean> {
        return api.getSongCategoryList()
            .registerLifeCycle(lifeCycleProvide)
            .doOnError(this::onHttpError)
            .doOnComplete(this::onHttpSuccess)
            .post()
    }

    private fun <T : IBean> onHttpSuccess(songCategoryBean: T) {
        Log.e(TAG, songCategoryBean.toString())
    }

    private fun onHttpError(error: Throwable) {
        error.printStackTrace()
    }

    private val TAG = this::class.java.simpleName
}