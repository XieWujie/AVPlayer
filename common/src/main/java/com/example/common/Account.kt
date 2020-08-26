package com.example.common

import android.content.SharedPreferences
import com.example.common.extension.int
import com.example.common.extension.string

/**
 * 使用前必须先调用init函数初始化
 */
class AccountAccessor private constructor( preferences: SharedPreferences) {

    var uid by preferences.int()
    var nickname by preferences.string()
    var avatarUrl by preferences.string()


    fun update(){
        Account.uid = uid
        Account.nickname = nickname?:""
        Account.avatarUrl = avatarUrl?:"https://p4.music.126.net/ma8NC_MpYqC-dK_L81FWXQ==/109951163250233892.jpg"
    }

    companion object{
       private lateinit var accountAccessor: AccountAccessor

        operator fun invoke() = accountAccessor

        fun init(preferences: SharedPreferences){
            accountAccessor = AccountAccessor(preferences)
            accountAccessor.update()
        }
    }

}

object Account{
    var uid:Int = 0
    var nickname:String = ""
    var avatarUrl:String = ""
}

