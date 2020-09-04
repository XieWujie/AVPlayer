package com.example.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dibus.*
import com.example.common.extension.toErrorLiveData
import com.example.login.CellPhoneLoginActivity
import com.example.login.LOGIN_SCOPE
import com.example.login.repository.ILoginRepository


class LoginViewModel @ViewModelService(CellPhoneLoginActivity::class)
constructor(private val repository:ILoginRepository):ViewModel(){


    @Scope(LOGIN_SCOPE)
     lateinit var lifeCycleProvide:AndroidLifeCycleProvide

    fun login(loginStrategy: LoginStrategy): LiveData<Throwable?>{
        val checkMessage = loginStrategy.check()
        if(!checkMessage.isNullOrEmpty()){
            val error = MutableLiveData<Throwable>()
            error.value = Throwable(checkMessage)
            return error
        }
        return repository.login(loginStrategy.account,loginStrategy.password).toErrorLiveData(
          diBus.scope(CellPhoneLoginActivity::class)
        )
    }

}
