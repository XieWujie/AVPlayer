package com.example.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conmon.base.AVViewModel
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.extension.toErrorLiveData
import com.example.login.http.LoginEntry
import com.example.login.repository.CellPhoneRepository
import com.example.login.repository.ILoginRepository

class LoginViewModel (repository: ILoginRepository,
                      override var lifeCycleProvide: AndroidLifeCycleProvide
):AVViewModel<ILoginRepository>(repository){

    fun login(loginStrategy: LoginStrategy): LiveData<Throwable?>{
        val checkMessage = loginStrategy.check()
        if(!checkMessage.isNullOrEmpty()){
            val error = MutableLiveData<Throwable>()
            error.value = Throwable(checkMessage)
            return error
        }
      val error = repository.login(loginStrategy.account,loginStrategy.password).toErrorLiveData(lifeCycleProvide)
        return error
    }
}
class CellPhoneViewModelFactory(val repository: CellPhoneRepository,private val lifeCycleProvide: AndroidLifeCycleProvide):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository,lifeCycleProvide) as T
    }
}