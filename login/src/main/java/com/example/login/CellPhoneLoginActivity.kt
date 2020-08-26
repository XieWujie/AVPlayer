package com.example.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.common.ACCOUNT
import com.example.common.PASSWORD
import com.example.common.base.AndroidLifeCycleProvide
import com.example.common.extension.toast
import com.example.login.databinding.CellPhoneActivityBinding
import com.example.login.vm.LoginStrategy
import com.example.login.vm.LoginViewModel
import com.example.route.AVRoute
import com.example.route.annotation.Route
import com.xie.di.AutoWire
import com.xie.di.DiBus
import com.xie.di.Provide

@Route("login/cellphone")
class CellPhoneLoginActivity : AppCompatActivity() {


    @AutoWire
    lateinit var viewModel: LoginViewModel

    @AutoWire
    lateinit var loginStrategy: LoginStrategy

    private lateinit var binding: CellPhoneActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiBus.addModelInfo("login")
        DiBus.register(this)
        binding = DataBindingUtil.setContentView(this, R.layout.cell_phone_activity)
        loginStrategy.password = intent.getStringExtra(PASSWORD) ?: ""
        loginStrategy.account = intent.getStringExtra(ACCOUNT) ?: ""
        binding.user = loginStrategy
        dispatchEvent()
    }

    @Provide(TAG = LIFE_CYCLE_TAG)
    fun provideLifeCycle() = AndroidLifeCycleProvide(this)

    private fun dispatchEvent() {
        val request = Observer<Throwable?> {
            when (it) {
                null -> {
                    AVRoute().route("main/main", this) {}.execute()
                }
                else -> toast(it.message ?: "请求出错")
            }
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(loginStrategy).observe(this, request)
        }
    }
}

internal const val LIFE_CYCLE_TAG = "life_cycle_tag"
