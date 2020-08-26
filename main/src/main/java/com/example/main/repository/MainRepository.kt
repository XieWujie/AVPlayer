package com.example.main.repository

import com.example.common.base.AndroidLifeCycleProvide
import com.example.common.base.IRepository
import com.example.main.http.MainApi

interface IMainRepository :IRepository{

}
class MainRepository(val api:MainApi,override val lifeCycleProvide: AndroidLifeCycleProvide) :IMainRepository{

}