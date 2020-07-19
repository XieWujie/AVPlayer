package com.example.main.repository

import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.IRepository
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.Playlist
import com.example.main.http.entry.SubCountEntry
import com.example.main.http.entry.UserSongList


interface IMineIRepository :IRepository{
    fun played():AVLiveData<PlayRecordList>
    fun subCount():AVLiveData<SubCountEntry>
    fun playList():AVLiveData<List<Playlist>>
}