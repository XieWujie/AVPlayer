package com.example.route

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.route.apt.IRouteRoot


class AVRoute private constructor(){

    @Throws(IllegalArgumentException::class)
    fun route(path:String,context: Context,avRouteContext:RouteInfo.(intent:Intent)->Unit = {}):RouteInfo{
       val activity = routeDispatcher.route(path)
        val intent = Intent(context,activity)
        val info = RouteInfo(intent){
            context.startActivity(intent)
        }
        avRouteContext(info,intent)
        return info
    }
    @Throws(IllegalArgumentException::class)
     fun getRouteActivityClass(path: String) = routeDispatcher.route(path)


    companion object{


        @Volatile
        private var isInit = false
        val routeDispatcher:RouteDispatcher by lazy { RouteDispatcher() }

        val avRoute by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){ AVRoute() }

        @Synchronized
        fun init(application: Application){
            if(isInit){
                return
            }
            isInit = true
            val classPaths:Set<String>
            if(CacheRoute.cacheCanUse(application)){
                classPaths = CacheRoute.routeMapCacheGet(application)!!
            }else{
                classPaths = ClassUtils.getFileNameByPackageName(application, PACKAGE_PATH)
                CacheRoute.cacheRoutePath(application,classPaths)
            }
            for(classPath in classPaths){
                if(classPath.startsWith(ROOT_ROUTE_PATH)){
                    (Class.forName(classPath).newInstance() as IRouteRoot).loadInto(routeDispatcher.indexMap)
                }
            }
        }

        operator fun invoke() = avRoute
    }
}