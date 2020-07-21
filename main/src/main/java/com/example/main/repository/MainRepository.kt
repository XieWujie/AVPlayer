package com.example.main.repository

import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.base.IRepository
import com.example.main.http.MainApi

interface IMainRepository :IRepository{

}
class MainRepository(val api:MainApi,override val lifeCycleProvide: AndroidLifeCycleProvide) :IMainRepository{

}