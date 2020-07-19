package com.example.main.repository

import android.content.SharedPreferences
import com.example.conmon.Account
import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.int
import com.example.main.http.MineApi
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.SubCountEntry

class MineRepository(mineLocal: MineLocal,val api:MineApi,override val lifeCycleProvide: AndroidLifeCycleProvide) :IMineIRepository{

    override fun subCount(): AVLiveData<SubCountEntry> {
       return api.getSubCount().registerLifeCycle(lifeCycleProvide)
           .doOnComplete(this::subCountComplete).post()
    }

    private fun subCountComplete(subCountEntry: SubCountEntry){

    }

    override fun played(): AVLiveData<PlayRecordList>{
       return api.playRecord(Account.uid).registerLifeCycle(lifeCycleProvide)
           .doOnComplete(this::played)
           .post()
    }

    private fun played(playRecordList: PlayRecordList){

    }

}

class MineLocal(preferences: SharedPreferences){
    var artistCount by preferences.int()
    var code by preferences.int()
    var createDjRadioCount by preferences.int()
    var createdPlaylistCount by preferences.int()
    var djRadioCount by preferences.int()
    var newProgramCount by preferences.int()
    var programCount by preferences.int()
    var subPlaylistCount by preferences.int()
}