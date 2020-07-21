package com.example.main.repository

import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.conmon.base.IRepository
import com.example.main.http.entry.DiscoveryApi

interface IDiscoveryRepository :IRepository {

}

class DiscoveryRepository(val api:DiscoveryApi,override val lifeCycleProvide: AndroidLifeCycleProvide) :IDiscoveryRepository{


}