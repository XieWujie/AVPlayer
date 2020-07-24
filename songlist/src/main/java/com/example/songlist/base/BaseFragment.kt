package com.example.songlist.base

import com.example.conmon.base.AVFragment
import com.example.conmon.base.AVViewModel

abstract class BaseFragment<R : AVViewModel<*>> : AVFragment<R>() {

}