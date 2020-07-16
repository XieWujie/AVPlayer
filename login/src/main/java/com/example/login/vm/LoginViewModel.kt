package com.example.login.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.login.http.LoginEntry
import com.example.login.repository.AbstractLoginRepository
import java.lang.Exception
import java.lang.RuntimeException

class LoginViewModel (val repository: AbstractLoginRepository):ViewModel(){


    fun login(loginStrategy: LoginStrategy, e: MutableLiveData<Exception>,
              data: MutableLiveData<LoginEntry>){
        val checkMessage = loginStrategy.check()
        if(checkMessage.isNullOrEmpty()){
            repository.login(loginStrategy.account,loginStrategy.password,e,data)
        }else{
            e.value = RuntimeException(checkMessage)
        }
    }



}
class CellPhoneViewModelFactory(val repository: AbstractLoginRepository):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}