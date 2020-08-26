package com.example.common.playBottomState

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.common.database.AppDbInstance
import com.example.common.database.SongDetailDao
import com.example.common.database.invokeMethod
import com.example.common.entity.SongDetail

class ViewModel :ViewModel(){


    private val dao by lazy { AppDbInstance.get().invokeMethod<SongDetailDao>("getSongDetail") }
    val songDetailLiveData = MutableLiveData<SongDetail>()
    private var beforeObserver:Observer<SongDetail>? = null
    private var beforeLiveData:LiveData<SongDetail>? = null

    fun getSongDetail(id:Int){
        beforeObserver?:beforeLiveData?.removeObserver(beforeObserver!!)
        beforeObserver = Observer {
            songDetailLiveData.value = it
        }
        beforeLiveData = dao.queryById(id).apply {
          observeForever(beforeObserver!!)
        }
    }

    override fun onCleared() {
        beforeObserver?:beforeLiveData?.removeObserver(beforeObserver!!)
    }
}