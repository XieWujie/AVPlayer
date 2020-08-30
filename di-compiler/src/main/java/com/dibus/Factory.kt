package com.dibus

import java.lang.ref.WeakReference

interface DiFactory<T>{

    fun get():T
}
