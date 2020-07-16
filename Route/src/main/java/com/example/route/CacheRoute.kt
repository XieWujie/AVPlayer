package com.example.route

import android.content.Context

object CacheRoute {

    private const val ROUTE_VERSION = "cache_version"
    private const val IS_HAVE_ROUTE_MAP = "is_have_route_map"
    private const val ROUTE_MAP = "route_map"
    fun cacheCanUse(context: Context):Boolean{
        val preferences = context.getSharedPreferences(ROUTE_MAP,Context.MODE_PRIVATE)
        val canUse = preferences.getBoolean(IS_HAVE_ROUTE_MAP,false)
        return canUse
    }

    fun cacheRoutePath(context: Context,classPaths:Set<String>){
        val preferences = context.getSharedPreferences(ROUTE_MAP,Context.MODE_PRIVATE)
        preferences.edit().apply {
            putStringSet(ROUTE_MAP,classPaths)
            putBoolean(IS_HAVE_ROUTE_MAP,true)
            apply()
        }
    }

    fun routeMapCacheGet(context: Context):Set<String>?{
        val preferences = context.getSharedPreferences(ROUTE_MAP,Context.MODE_PRIVATE)
        return preferences.getStringSet(ROUTE_MAP,null)
    }
}