package com.example.main.repository

import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.IRepository
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.SubCountEntry


interface IMineIRepository :IRepository{
    fun played():AVLiveData<PlayRecordList>
    fun subCount():AVLiveData<SubCountEntry>
}