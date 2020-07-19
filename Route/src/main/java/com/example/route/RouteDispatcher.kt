package com.example.route

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.example.route.core.IRouteGroup

class RouteDispatcher {

    val indexMap = HashMap<String,Class<out IRouteGroup>>()
    val pathMap = HashMap<String,Class<*>>()

    @Throws(IllegalArgumentException::class)
    @SuppressWarnings("unchecked cast")
    fun route(path:String):Class<out FragmentActivity>{
        if(pathMap.containsKey(path)){
            val clazz =  pathMap[path]!!
            checkClassType(clazz)
            return clazz as Class<out FragmentActivity>
        }
        loadgroupRoute(path)
        val clazz = pathMap[path]?:throw IllegalArgumentException("找不到指定路径$path")
        checkClassType(clazz)
        return clazz as Class<out FragmentActivity>
    }

    private fun checkClassType(clazz: Class<*>){
        if (!Activity::class.java.isAssignableFrom(clazz)){
            throw RuntimeException("@Route 只能在Activity下使用，而不是${clazz.canonicalName.toString()}")
        }
    }



    @Throws(IllegalArgumentException::class)
    private fun loadgroupRoute(path:String){
        val rootName = findRootName(path)?:throw IllegalArgumentException("找不到指定分组$path")
        val group = indexMap[rootName]?:throw IllegalArgumentException("找不到指定分组$path")
        val groupRoute = group.newInstance()
        groupRoute.loadInto(pathMap)
    }


    private fun findRootName(path:String):String?{
        val index = path.indexOf("/")
        if(index == -1){
            return null
        }
        return path.substring(0,index)
    }
}