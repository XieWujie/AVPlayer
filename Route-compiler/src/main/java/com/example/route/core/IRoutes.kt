package com.example.route.core

interface IRouteRoot {

    fun loadInto(routes: HashMap<String, Class<out IRouteGroup>>)
}

interface IRouteGroup{

    fun loadInto(groups:HashMap<String,Class<*>>)
}