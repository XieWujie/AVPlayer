package com.example.conmon.adapter

interface Transformer<T>{

    fun<R> transform(invoke:T.()->R):Transformer<R>

}
