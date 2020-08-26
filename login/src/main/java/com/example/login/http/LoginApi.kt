package com.example.login.http

import com.example.common.adapter.AVLiveData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

 interface LoginApi{

    @GET("/login/cellphone")
     fun loginByCellPhone(@Query("phone")phone:String,@Query("md5_password")md5Password:String):AVLiveData<LoginEntry>


    @POST("/login/")
     fun loginByEmail(@Query("email")phone:String,@Query("password")password:String):AVLiveData<LoginEntry>
}