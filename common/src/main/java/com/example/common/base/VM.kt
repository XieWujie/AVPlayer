package com.example.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface ErrorAware{
     val error:MutableLiveData<Throwable>
}
abstract class AVViewModel<R:IRepository>(protected val repository:R):ViewModel(),ErrorAware{

    abstract var lifeCycleProvide:AndroidLifeCycleProvide
    override var error: MutableLiveData<Throwable> = MutableLiveData()

}
