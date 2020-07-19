package com.example.login.di

import androidx.lifecycle.ViewModelProvider
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.login.CellPhoneLoginActivity
import com.example.login.http.LoginApi
import com.example.login.repository.CellPhoneRepository
import com.example.login.vm.CellPhoneLogin
import com.example.login.vm.CellPhoneViewModelFactory
import com.example.login.vm.LoginViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit

private const val CELL_PHONE_ACTIVITY_MODULE = "cell_phone_activity_module"

val cellPhoneModule = Kodein.Module(CELL_PHONE_ACTIVITY_MODULE){
    bind<LoginApi>() with provider {
        instance<Retrofit>().create(LoginApi::class.java)
    }
    bind<AndroidLifeCycleProvide>() with provider {
        AndroidLifeCycleProvide(instance<CellPhoneLoginActivity>())
    }
    bind<CellPhoneRepository>() with provider {
        CellPhoneRepository(instance(),instance())
    }
    bind<CellPhoneLogin>() with provider {
        CellPhoneLogin()
    }

    bind<CellPhoneViewModelFactory>() with provider {
        CellPhoneViewModelFactory(instance<CellPhoneRepository>(),instance())
    }
    bind<LoginViewModel>() with provider {
        ViewModelProvider(instance<CellPhoneLoginActivity>(),instance<CellPhoneViewModelFactory>())[LoginViewModel::class.java]
    }
}