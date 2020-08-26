package com.example.songlist.base

import com.example.common.base.AVFragment
import com.example.common.base.AVViewModel

abstract class BaseFragment<R : AVViewModel<*>> : AVFragment<R>() {

}