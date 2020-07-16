package com.example.login.repository

import androidx.lifecycle.MutableLiveData
import com.example.login.http.LoginEntry
import java.util.function.Function

interface AbstractLoginRepository {

    fun login(
        account: String,
        password: String,
        e: MutableLiveData<Exception>,
        data: MutableLiveData<LoginEntry>
    )

}