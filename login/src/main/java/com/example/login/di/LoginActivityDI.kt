package com.example.login.di

import com.example.login.CellPhoneLoginActivity
import com.example.login.http.LoginApi
import com.example.login.repository.CellPhoneRepository
import com.xie.di.AutoWire
import com.xie.di.DiBus
import com.xie.di.Provide
import com.xie.di.Service
import retrofit2.Retrofit


@Service
class LoginActivityDI {



    @AutoWire
    lateinit var repository: CellPhoneRepository

    @AutoWire
    lateinit var cellPhoneLoginActivity: CellPhoneLoginActivity

    @Provide
    fun loginApi():LoginApi{
        return DiBus.load<Retrofit>().create(LoginApi::class.java)
    }

}