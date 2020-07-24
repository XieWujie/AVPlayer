package com.example.route.apt

interface IRouteRoot {

    fun loadInto(routes: HashMap<String, Class<out IRouteGroup>>)
}

interface IRouteGroup{

    fun loadInto(groups:HashMap<String,Class<*>>)
}