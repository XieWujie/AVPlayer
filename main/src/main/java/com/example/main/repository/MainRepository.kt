package com.example.main.repository

import com.example.common.base.IRepository
import com.example.main.http.MainApi
import com.xie.di.Service

interface IMainRepository :IRepository
class MainRepository @Service constructor(val api:MainApi) :IMainRepository