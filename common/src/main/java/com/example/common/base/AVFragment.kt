package com.example.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.xie.di.DiBus
import com.xie.di.Provide
import com.xie.di.Utils
import org.kodein.di.android.x.closestKodein

@Suppress("LeakingThis")
abstract class AVFragment :Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiBus.getInstance().registerLifeCycle(this)
    }
}