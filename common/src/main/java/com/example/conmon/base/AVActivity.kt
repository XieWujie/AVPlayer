package com.example.conmon.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import org.kodein.di.android.closestKodein

abstract class AVActivity<R:AVViewModel<*>> : AppCompatActivity(),ErrorAware{

    protected val parent by closestKodein()
    protected val lifeCycleProvide = AndroidLifeCycleProvide(this)
    abstract  val viewModel: R

    override val error: MutableLiveData<Throwable> by lazy { viewModel.error }
}