package com.example.songlist.repository

import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.IRepository
import com.example.songlist.bean.HeightQualitySongListBean
import com.example.songlist.bean.SongListBean
import retrofit2.http.Part

interface ISongSquareFragmentRepository : IRepository {
    /**
     * 获取精品歌单
     * @param before:分页 前一页的最后一个歌单的 [HeightQualitySongListBean] ->[Playlists] -> updateTime
     */
    fun getHeightQualitySongList(
        before: Long = 0,
        limit: Int = 18,
        cat: String = "全部"
    ): AVLiveData<HeightQualitySongListBean>

    /**
     * 获取歌单
     * @param offset: 分页偏移量
     */
    fun getSongList(
        cat: String = "全部",
        offset: Int = 0,
        limit: Int = 18,
        order: String = "hot"
    ): AVLiveData<SongListBean>
}