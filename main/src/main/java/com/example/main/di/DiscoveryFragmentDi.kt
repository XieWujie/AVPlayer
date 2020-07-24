package com.example.main.di

import androidx.lifecycle.ViewModelProvider
import com.example.main.MainActivity
import com.example.main.adapter.discovery.DiscoveryTopAdapter
import com.example.main.http.DiscoveryApi
import com.example.main.repository.DiscoveryRepository
import com.example.main.repository.IDiscoveryRepository
import com.example.main.view.DiscoveryFragment
import com.example.main.viewmodel.DiscoveryViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit

private const val TAG = "discovery_frament_di"

val DISCOVERY_FRAGMENT_MODULE = Kodein.Module(TAG){


    bind<DiscoveryApi>() with provider {
        instance<Retrofit>().create(DiscoveryApi::class.java)
    }
    bind<IDiscoveryRepository>() with provider {
        DiscoveryRepository(instance(),instance())
    }
    bind<DiscoveryViewModel>() with provider {
        ViewModelProvider(instance<MainActivity>(),
            DiscoveryViewModel.getFactory(instance(),instance()))[DiscoveryViewModel::class.java]
    }
}