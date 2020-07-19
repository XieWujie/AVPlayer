package com.example.login
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.conmon.ACCOUNT
import com.example.conmon.PASSWORD
import com.example.conmon.base.AVActivity
import com.example.conmon.extension.toast
import com.example.login.databinding.CellPhoneActivityBinding
import com.example.login.di.cellPhoneModule
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
class CellPhoneLoginActivity : AVActivity<LoginViewModel>(),KodeinAware {


    override val kodein:Kodein= Kodein.lazy {
        extend(parent)
        import(cellPhoneModule)
        bind<CellPhoneLoginActivity>() with scoped(AndroidLifecycleScope).singleton {this@CellPhoneLoginActivity}
    }

    override val viewModel: LoginViewModel by instance()
    private val loginStrategy  by instance<LoginStrategy>()
    private lateinit var binding:CellPhoneActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.cell_phone_activity)
        loginStrategy.password= intent.getStringExtra(PASSWORD)?:""
        loginStrategy.account = intent.getStringExtra(ACCOUNT)?:""
        binding.user = loginStrategy
        dispatchEvent()
    }


    private fun dispatchEvent(){
        val request = Observer<Throwable?> {
            when(it){
                null->{
                    AVRoute().route("main/main",this).execute()
                }
                else->toast(it.message?:"请求出错")
            }
        }
         binding.loginButton.setOnClickListener {
            viewModel.login(loginStrategy).observe(this,request)
        }
    }
}
