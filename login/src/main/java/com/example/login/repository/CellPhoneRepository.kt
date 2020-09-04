package com.example.login.repository

import com.dibus.AndroidLifeCycleProvide
import com.dibus.Scope
import com.dibus.Service
import com.dibus.diBus
import com.example.common.AccountAccessor
import com.example.common.adapter.AVLiveData
import com.example.common.util.Md5Encrypt
import com.example.login.CellPhoneLoginActivity
import com.example.login.LOGIN_SCOPE
import com.example.login.http.LoginApi
import com.example.login.http.LoginEntry


class CellPhoneRepository @Service constructor(private val api: LoginApi) :ILoginRepository{


    @Scope(LOGIN_SCOPE)
  lateinit var lifeCycleProvide:AndroidLifeCycleProvide


    override fun login(phone: String, password: String):AVLiveData<LoginEntry>{
        val md5Psw = Md5Encrypt.getMd5(password,32)
        return api.loginByCellPhone(phone,md5Psw)
            .doOnError {
                it.printStackTrace()
            }
            .doOnComplete(this::onSuccess).post(diBus.scope(CellPhoneLoginActivity::class))
    }

    private fun onSuccess(loginEntry: LoginEntry){

        AccountAccessor().apply {
            this.uid = loginEntry.account.id
            this.nickname = loginEntry.profile.nickname
            this.avatarUrl = loginEntry.profile.avatarUrl
            update()
        }
    }

}