package com.example.login.http

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

public interface LoginApi{

    @POST("login/cellphone")
    fun loginByCellPhone(@Query("phone")phone:String,@Query("md5_password")md5Password:String):Deferred<LoginEntry>


    @POST("login/")
    fun loginByEmail(@Query("email")phone:String,@Query("password")password:String):Deferred<LoginEntry>
}