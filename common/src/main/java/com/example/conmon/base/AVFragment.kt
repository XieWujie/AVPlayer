package com.example.conmon.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.kodein.di.android.x.closestKodein

@Suppress("LeakingThis")
abstract class AVFragment<R:AVViewModel<*>> :Fragment(),ErrorAware{

    protected val parent by closestKodein()
    protected val lifeCycleProvide = AndroidLifeCycleProvide(this)
    abstract  val viewModel: R
    override val error: MutableLiveData<Throwable> by lazy { viewModel.error }

}