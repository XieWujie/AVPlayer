package com.example.common.adapter

import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        @JvmStatic @JvmName("create")
        operator fun invoke() = LiveDataCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (AVLiveData::class.java != getRawType(returnType)) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "LiveData return type must be parameterized as Deferred<Foo> or Deferred<out Foo>")
        }
        val responseType = getParameterUpperBound(0, returnType)

        val rawType = getRawType(responseType)
        return if (rawType == Response::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalStateException(
                    "Response must be parameterized as Response<Foo> or Response<out Foo>")
            }
            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }

    private class BodyCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, AVLiveData<T>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>):AVLiveData<T> {
            val liveData = AVLiveData<T>()
            liveData.registerCancelEvent {
                call.cancel()
            }
            call.enqueue(object :Callback<T>{
                override fun onFailure(call: Call<T>, t: Throwable) {
                    liveData.postError(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    when(response.code()){
                        200->liveData.postValueOfTarget(response.body()!!)
                        300,301->call.request()
                        else ->{
                            liveData.postError(Throwable(response.message()?:response.message()?:"请求错误"))
                        }
                    }
                }
            })
            return liveData
        }
    }

    private class ResponseCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, AVLiveData<Response<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): AVLiveData<Response<T>> {
            val liveData = AVLiveData<Response<T>>()
            liveData.registerCancelEvent { call.cancel() }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    liveData.postError(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    when (response.code()) {
                        200 -> liveData.postValueOfTarget(response)
                        else -> {
                            liveData.postError(
                                Exception(
                                    response.message() ?: response.errorBody()?.string() ?: "请求错误"
                                )
                            )
                        }
                    }
                }
            })
            return liveData
        }
    }
}