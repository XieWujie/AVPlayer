package com.example.conmon.extension

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

inline fun<reified R> Deferred<R>.launcherIO(data:MutableLiveData<R>, error:MutableLiveData<Exception>){
    GlobalScope.launch(Dispatchers.IO){
        var realData:R? = null
        var exc:Exception?= null
        try {
            realData = await()
        }catch (e:Exception){
            exc = e
        }
        async(Dispatchers.Main){
            realData?.apply { data.value = this }
            exc?.apply { error.value = this }
        }
    }
}
