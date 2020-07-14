package com.example.conmon.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

inline fun<reified T : ViewDataBinding> LayoutInflater.bind(layoutId:Int, parent: ViewGroup?) = DataBindingUtil.inflate<T>(this,layoutId,parent,false)