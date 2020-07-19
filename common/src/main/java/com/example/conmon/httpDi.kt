package com.example.conmon

import com.example.conmon.adapter.LiveDataCallAdapterFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_CLIENT_MODUEL = "http_client_module"
private  const val  BASE_URL = "http://10.0.2.2:3000"
private const val AUTH_INTERCEPT = "authorization_intercept"

val httpClientModule = Kodein.Module(HTTP_CLIENT_MODUEL){
    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }
    bind<OkHttpClient.Builder>() with provider { OkHttpClient.Builder() }
    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .client(instance())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }
    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .readTimeout(8000, TimeUnit.MILLISECONDS)
           // .addInterceptor(instance(AUTH_INTERCEPT))
            .build()
    }
    bind<Gson>() with singleton { Gson() }
}