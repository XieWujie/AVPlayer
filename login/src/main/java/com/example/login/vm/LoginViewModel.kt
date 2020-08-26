package com.example.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.common.extension.toErrorLiveData
import com.example.login.CellPhoneLoginActivity
import com.example.login.LIFE_CYCLE_TAG
import com.example.login.repository.CellPhoneRepository
import com.example.login.repository.ILoginRepository
import com.xie.di.AndroidLifeCycleProvide
import com.xie.di.AutoWire
import com.xie.di.DiBus
import com.xie.di.ViewModelService


class LoginViewModel @ViewModelService(CellPhoneLoginActivity::class)
constructor(private val repository:ILoginRepository):ViewModel(){


     val lifeCycleProvide:AndroidLifeCycleProvide = DiBus.getInstance().lifeCycle<CellPhoneLoginActivity>()


    fun login(loginStrategy: LoginStrategy): LiveData<Throwable?>{
        val checkMessage = loginStrategy.check()
        if(!checkMessage.isNullOrEmpty()){
            val error = MutableLiveData<Throwable>()
            error.value = Throwable(checkMessage)
            return error
        }
        return repository.login(loginStrategy.account,loginStrategy.password).toErrorLiveData(
            lifeCycleProvide
        )
    }

}
