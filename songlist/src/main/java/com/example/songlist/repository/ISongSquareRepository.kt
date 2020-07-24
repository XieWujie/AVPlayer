package com.example.songlist.repository

import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.IRepository
import com.example.songlist.bean.SongCategoryBean

interface ISongSquareRepository : IRepository {

    fun getSongCategoryList(): AVLiveData<SongCategoryBean>
}