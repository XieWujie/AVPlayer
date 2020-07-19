package com.example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.conmon.ACCOUNT
import com.example.conmon.Account
import com.example.conmon.PASSWORD
import com.example.route.AVRoute
import com.example.route.annotation.Route

@Route("main/main")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkAccount()
    }

    private fun checkAccount(){
        val uid = Account.uid
        if(uid == 0){
            AVRoute().route("login/cellphone",this){
                ACCOUNT with "13677626587"
                PASSWORD with "wyy123456"
            }.execute()
        }
    }
}
