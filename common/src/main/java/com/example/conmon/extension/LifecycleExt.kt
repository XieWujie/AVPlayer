package com.example.conmon.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.base.LifeCycleProvide


inline fun< reified R> viewModelFactory(crossinline creator:()->R) = object: ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator.invoke() as T
    }
}

inline fun<reified T : ViewDataBinding> LayoutInflater.bind(layoutId:Int, parent: ViewGroup?) = DataBindingUtil.inflate<T>(this,layoutId,parent,false)

inline fun<reified T> LiveData<T>.lifecycleObserve(provider:AndroidLifeCycleProvide,observer: Observer<T>){
    this.observeForever(observer)
    provider.provide{
        removeObserver(observer)
    }

}
inline fun<reified T> AVLiveData<T>.lifecycleObserve(provider:AndroidLifeCycleProvide,observer: Observer<AVLiveData.AVLiveDataWrap<T>>){
    this.observeForever(observer)
    provider.provide{
        removeObserver(observer)
        if(!hasCanceled()){
            cancel()
        }
    }

}

inline fun<reified T> AVLiveData<T>.lifecycleHandle(provider:AndroidLifeCycleProvide, crossinline success:(data:T)->Unit,crossinline error:(error:Throwable)->Unit){
    val observer =  Observer<AVLiveData.AVLiveDataWrap<T>>{
        val value =value()!!
        when(value.error){
            null->success.invoke(value.data!!)
            else->error.invoke(value.error!!)
        }
    }
    this.observeForever(observer)
    provider.provide{
        removeObserver(observer)
        if(!hasCanceled()){
            cancel()
        }
    }

}

inline fun <reified T>AVLiveData<T>.toErrorLiveData(lifeCycleProvide: AndroidLifeCycleProvide):LiveData<Throwable?>{
    val error = MutableLiveData<Throwable>()
    this.doOnError { error.value = this.value()?.error }
        .doOnComplete { error.value = null }
        .registerLifeCycle(lifeCycleProvide)
        .post()
    return error
}