package com.example.main.repository

import com.example.common.adapter.AVLiveData
import com.example.common.base.IRepository
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.Playlist
import com.example.main.http.entry.SubCountEntry


interface IMineIRepository :IRepository{
    fun played():AVLiveData<PlayRecordList>
    fun subCount():AVLiveData<SubCountEntry>
    fun playList():AVLiveData<List<Playlist>>
}