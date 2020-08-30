package com.example.login.vm

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dibus.AutoWire
import com.dibus.Service

abstract class LoginStrategy :BaseObservable(){
    @Bindable var account:String = ""
    @Bindable var password:String = ""
    abstract fun check():String?
}

@Service
class CellPhoneLogin:LoginStrategy(){

    override fun check(): String? {
        if(password.isEmpty()){
            return "密码不能为空"
        }
        if(account.isEmpty()){
            return "手机号不能为空"
        }
        return null
    }

}

class Test{

    @AutoWire
  public lateinit var login:Context
}