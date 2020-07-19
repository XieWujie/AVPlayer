package com.example.login.repository

import com.example.conmon.AccountAccessor
import com.example.conmon.adapter.AVLiveData
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.util.Md5Encrypt
import com.example.login.http.LoginApi
import com.example.login.http.LoginEntry


class CellPhoneRepository(private val api: LoginApi, override val lifeCycleProvide: AndroidLifeCycleProvide
) :ILoginRepository{

    override fun login(phone: String, password: String):AVLiveData<LoginEntry>{
        val md5Psw = Md5Encrypt.getMd5(password,32)
        return api.loginByCellPhone(phone,md5Psw).registerLifeCycle(lifeCycleProvide)
            .doOnError {
                it.printStackTrace()
            }
            .doOnComplete(this::onSuccess).post()
    }

    fun onSuccess(loginEntry: LoginEntry){

        AccountAccessor().apply {
            this.uid = loginEntry.account.id
            this.nickname = loginEntry.profile.nickname
            this.avatarUrl = loginEntry.profile.avatarUrl
            update()
        }
    }

}