package com.example.route

import android.content.Context
import android.content.Intent
import com.example.route.annotation.Route
import com.example.route.core.AbstractRoutes

class AVRoute private constructor(){
    private val routes = HashMap<String,Class<*>>()

    fun loadRoutes(){
        val routesClass = Class.forName("com.example.route.core.RealRoutes");
        val realRoutes = routesClass.newInstance() as AbstractRoutes
        realRoutes.loadRoutes(routes)
    }



    companion object{

        val avRoute by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            AVRoute().apply {
                loadRoutes()
            }
        }

        fun route(path:String,context: Context,avRouteContext:RouteInfo.(intent:Intent)->Unit):RouteInfo{
           if(!avRoute.routes.containsKey(path)){
               throw IllegalArgumentException("path not find")
           }
            val intent = Intent(context,avRoute.routes[path]);
            val routeInfo = RouteInfo(intent){
                context.startActivity(intent)
            }
            return routeInfo
        }
    }
}