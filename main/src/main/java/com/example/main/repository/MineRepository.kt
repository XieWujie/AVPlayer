package com.example.main.repository

import android.content.SharedPreferences
import com.example.common.Account
import com.example.common.adapter.AVLiveData
import com.example.common.base.AndroidLifeCycleProvide
import com.example.common.extension.int
import com.example.main.http.MineApi
import com.example.main.http.entry.PlayRecordList
import com.example.main.http.entry.Playlist
import com.example.main.http.entry.SubCountEntry

class MineRepository(val mineLocal: MineLocal,val api:MineApi,override val lifeCycleProvide: AndroidLifeCycleProvide) :IMineIRepository{

    override fun playList(): AVLiveData<List<Playlist>> {
        val playList = AVLiveData<List<Playlist>>()
        api.userSongList(Account.uid).registerLifeCycle(lifeCycleProvide)
            .doOnComplete { playList.value(it.playlist)}
            .doOnError {
                playList.error(it)
                it.printStackTrace()
            }
            .post()
        return playList
    }

    override fun subCount(): AVLiveData<SubCountEntry> {
       return fromCacheSubCount()?: api.getSubCount().registerLifeCycle(lifeCycleProvide)
           .doOnComplete(this::subCountComplete).post()
    }

    private fun subCountComplete(subCountEntry: SubCountEntry){
        with(subCountEntry){
            mineLocal.artistCount = artistCount
            mineLocal.code = code
            mineLocal.createDjRadioCount = createDjRadioCount
            mineLocal.createdPlaylistCount = createdPlaylistCount
            mineLocal.djRadioCount = djRadioCount
            mineLocal.newProgramCount = newProgramCount
            mineLocal.subPlaylistCount = subPlaylistCount
            mineLocal.mvCount = mvCount
            mineLocal.programCount = programCount
        }
    }

    private fun fromCacheSubCount():AVLiveData<SubCountEntry>?{
        if (mineLocal.djRadioCount == -1){
            return null
        }
        val data = AVLiveData<SubCountEntry>()
        mineLocal.apply {
            val subCountEntry =
                SubCountEntry(
                    artistCount, code, createDjRadioCount, createdPlaylistCount,
                    djRadioCount, mvCount, newProgramCount, programCount, subPlaylistCount
                )
            data.value(subCountEntry)
        }
        return data
    }

    override fun played(): AVLiveData<PlayRecordList>{
       return api.playRecord(Account.uid)
           .registerLifeCycle(lifeCycleProvide)
           .doOnError { it.printStackTrace() }
           .doOnComplete(this::played)
           .post()
    }

    private fun played(playRecordList: PlayRecordList){
       // Log.d("MineRepository",playRecordList.toString())
    }

}

class MineLocal(preferences: SharedPreferences){
    var artistCount by preferences.int()
    var code by preferences.int()
    var createDjRadioCount by preferences.int()
    var createdPlaylistCount by preferences.int()
    var djRadioCount by preferences.int(defaultValue = -1)
    var mvCount by preferences.int()
    var newProgramCount by preferences.int()
    var programCount by preferences.int()
    var subPlaylistCount by preferences.int()
}