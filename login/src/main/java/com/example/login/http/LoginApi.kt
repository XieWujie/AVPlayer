package com.example.login.http

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginCellPhone{

    @POST("login/cellphone")
    fun loginByCellPhone(@Query("phone")phone:String,@Query("md5_password")md5Password:String)
}