package com.example.common.adapter

interface Transformer<T>{
    fun<R> transform(invoke:T.()->R):Transformer<R>

}
