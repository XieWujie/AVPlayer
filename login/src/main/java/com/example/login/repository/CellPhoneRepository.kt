package com.example.login.repository

import androidx.lifecycle.MutableLiveData
import com.example.conmon.extension.launcherIO
import com.example.conmon.util.Md5Encrypt
import com.example.login.http.LoginApi
import com.example.login.http.LoginEntry


class CellPhoneRepository(private val api: LoginApi) : AbstractLoginRepository {
    override fun login(
        phone: String,
        password: String,
        e: MutableLiveData<Exception>,
        data: MutableLiveData<LoginEntry>
    ) {
        val md5Psw = Md5Encrypt.encrypt(password)
        val deferred = api.loginByCellPhone(phone, md5Psw)
        deferred.launcherIO(data, e)
    }
}