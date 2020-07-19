package com.example.login.repository

import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.IRepository
import com.example.login.http.LoginEntry

interface ILoginRepository: IRepository {

    fun login(account: String, password: String):AVLiveData<LoginEntry>
}