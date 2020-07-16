package com.example.login
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.conmon.ACCOUNT
import com.example.conmon.BaseActivity
import com.example.conmon.PASSWORD
import com.example.conmon.extension.toast
import com.example.login.databinding.CellPhoneActivityBinding
import com.example.login.di.cellPhoneModule
import com.example.login.http.LoginEntry
import com.example.login.vm.LoginStrategy
import com.example.login.vm.LoginViewModel
import com.example.route.AVRoute
import com.example.route.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

@Route( "login/cellphone")
class CellPhoneLoginActivity :BaseActivity(),KodeinAware {


    override val kodein:Kodein= Kodein.lazy {
        extend(parent)
        import(cellPhoneModule)
        bind<CellPhoneLoginActivity>() with scoped(AndroidLifecycleScope).singleton {this@CellPhoneLoginActivity}
    }

    private val viewModel:LoginViewModel by instance()
    private val loginStrategy  by instance<LoginStrategy>()
    private lateinit var binding:CellPhoneActivityBinding
    private val data = MutableLiveData<LoginEntry>()
    private val error = MutableLiveData<Exception>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.cell_phone_activity)
        loginStrategy.password= intent.getStringExtra(PASSWORD)?:""
        loginStrategy.account = intent.getStringExtra(ACCOUNT)?:""
        binding.user = loginStrategy
        dispatchEvent()
    }


    private fun dispatchEvent(){
        binding.loginButton.setOnClickListener {
            viewModel.login(loginStrategy,error,data)
        }
        data.observe(this, Observer {
            AVRoute().route("app/main",this).execute()
        })
        error.observe(this, Observer {
            if(!it.message.isNullOrEmpty()){
                toast(it.message!!)
            }
        })
    }
}
