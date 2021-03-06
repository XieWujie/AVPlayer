@file:Suppress("UNCHECKED_CAST")

package com.example.common.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.example.common.adapter.AVLiveData
import com.dibus.AndroidLifeCycleProvide


inline fun< reified R> viewModelFactory(crossinline creator:()->R) = object: ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator.invoke() as T
    }
}

inline fun<reified T : ViewDataBinding> LayoutInflater.bind(layoutId:Int, parent: ViewGroup?): T = DataBindingUtil.inflate<T>(this,layoutId,parent,false)

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


inline fun <reified T>AVLiveData<T>.toErrorLiveData(lifecycleOwner: LifecycleOwner):LiveData<Throwable?>{
    val error = MutableLiveData<Throwable>()
    this.doOnError { error.value = getError() }
        .doOnComplete { error.value = null }
        .post(lifecycleOwner)
    return error
}