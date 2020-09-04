package com.example.common.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dibus.AndroidLifeCycleProvide


class AVLiveData<T> :LiveData<AVLiveData.AVLiveDataWrap<T>>(){

    private var dataWrap:AVLiveDataWrap<T>? = null
    private var canceled = false
    private var call:(()->Unit)?  = null
    private var onComplete:((data:T)->Unit)? = null
    private var error:((error:Throwable)->Unit)? = null


    fun postError(throwable: Throwable){
        dataWrap = AVLiveDataWrap(throwable,null)
        throwable.printStackTrace()
        postValue(dataWrap)
    }
    fun hasCanceled() = canceled

    fun registerCancelEvent(call:()->Unit){
        this.call = call
    }


    override fun onInactive() {
        if(!hasCanceled()){
            cancel()
        }
    }

    fun doOnComplete(block:(data:T)->Unit):AVLiveData<T>{
        this.onComplete = block
        return this
    }

    fun doOnError(block:(error:Throwable)->Unit):AVLiveData<T>{
        this.error = block
        return this
    }

    fun post(lifecycleOwner: LifecycleOwner):AVLiveData<T>{
        val complete = onComplete
        val er = error
        val observer =  Observer<AVLiveDataWrap<T>>{
            val value =dataWrap!!
            when(value.error){
                null->complete?.invoke(value.data!!)
                else->er?.invoke(value.error!!)
            }
        }
        this.observe(lifecycleOwner,observer)
        onComplete = null
        error = null
        return this
    }


     fun postValueOfTarget(value:T){
        dataWrap = AVLiveDataWrap(null,value)
         super.postValue(dataWrap)
    }

    fun cancel(){
        canceled = true
        call?.invoke()
        call =null
    }

    fun value(value: T){
        dataWrap = AVLiveDataWrap(null,value)
        super.setValue(dataWrap)
    }

    fun getData() = dataWrap?.data
    fun getError() = dataWrap?.error

    fun error(error: Throwable){
        dataWrap = AVLiveDataWrap(error,null)
        super.setValue(dataWrap)
    }



   data class AVLiveDataWrap<T>(var error:Throwable?,var data:T?)


}