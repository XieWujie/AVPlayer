package com.example.login.repository

import com.example.common.adapter.AVLiveData
import com.example.login.http.LoginEntry

interface ILoginRepository {

    fun login(account: String, password: String):AVLiveData<LoginEntry>
}