package com.example.conmon

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.android.closestKodein

open class BaseActivity : AppCompatActivity(){
    protected val parent by closestKodein()

}