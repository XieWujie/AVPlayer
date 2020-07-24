package com.example.songlist.http

import com.example.conmon.adapter.AVLiveData
import com.example.songlist.bean.HeightQualitySongListBean
import com.example.songlist.bean.SongCategoryBean
import com.example.songlist.bean.SongListBean
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.ByteOrder

interface SongSquareApi {

    /**
     * 获取歌单分类 歌单->标签
     */
    @GET("/playlist/catlist")
    fun getSongCategoryList(): AVLiveData<SongCategoryBean>


    @GET("/top/playlist/highquality")
    fun getHeightQualitySongList(
        @Query("before") before: Long,
        @Query("limit") limit: Int,
        @Query("cat") cat: String
    ): AVLiveData<HeightQualitySongListBean>


    @GET("/top/playlist")
    fun getSongList(
        @Query("cat") cat: String = "全部",
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 18,
        @Query("order") order: String = "hot"
    ): AVLiveData<SongListBean>
}