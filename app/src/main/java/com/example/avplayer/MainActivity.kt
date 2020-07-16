package com.example.avplayer

import android.os.Bundle
import com.example.conmon.ACCOUNT
import com.example.conmon.BaseActivity
import com.example.route.AVRoute
import com.example.route.annotation.Route

@Route("app/main")
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AVRoute().route("login/cellphone",this){
            ACCOUNT with "xiewujie"
        }
            .execute()
    }
}
