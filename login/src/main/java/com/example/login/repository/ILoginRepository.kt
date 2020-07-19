package com.example.login.repository

import androidx.lifecycle.LiveData
import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.base.IRepository
import com.example.login.http.LoginEntry

interface ILoginRepository: IRepository {

    fun login(account: String, password: String):AVLiveData<LoginEntry>
}