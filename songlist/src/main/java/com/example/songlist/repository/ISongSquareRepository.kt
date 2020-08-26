package com.example.songlist.repository

import com.example.common.adapter.AVLiveData
import com.example.common.base.IRepository
import com.example.songlist.bean.SongCategoryBean

interface ISongSquareRepository : IRepository {

    fun getSongCategoryList(): AVLiveData<SongCategoryBean>
}