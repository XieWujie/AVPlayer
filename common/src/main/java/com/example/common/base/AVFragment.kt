package com.example.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.xie.di.DiBus

@Suppress("LeakingThis")
abstract class AVFragment :Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiBus.getInstance().registerLifeCycle(this)
    }
}