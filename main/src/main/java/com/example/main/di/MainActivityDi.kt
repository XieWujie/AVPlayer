package com.example.main.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.conmon.base.AndroidLifeCycleProvide
import com.example.main.MainActivity
import com.example.main.adapter.mine.PageTabAdapter
import com.example.main.http.MainApi
import com.example.main.repository.IMainRepository
import com.example.main.repository.MainRepository
import com.example.main.view.DiscoveryFragment
import com.example.main.view.MineFragment
import com.example.main.viewmodel.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.*
import retrofit2.Retrofit
import java.util.*

private const val TAG = "main_activity_di"

val MAIN_ACTIVITY_MODULE = Kodein.Module(TAG){
    bind<MineFragment>() with scoped(AndroidLifecycleScope).singleton{ MineFragment() }
    bind<DiscoveryFragment>() with scoped(AndroidLifecycleScope).singleton { DiscoveryFragment() }
    bind<FragmentManager>() with provider {
        instance<MainActivity>().supportFragmentManager
    }
    bind<PageTabAdapter>() with provider {
        val list =ArrayList<Fragment>().apply {
            add(instance<MineFragment>())
            add(instance<DiscoveryFragment>())
        }
        PageTabAdapter(instance(), list)
    }

    bind<MainApi>() with provider {
        instance<Retrofit>().create(MainApi::class.java)
    }
    bind<AndroidLifeCycleProvide>() with provider { AndroidLifeCycleProvide(instance<MainActivity>()) }
    bind<IMainRepository>() with provider { MainRepository(instance(),instance()) }
    bind<MainViewModel>() with provider { ViewModelProvider(instance<MainActivity>(),MainViewModel.getFactory(instance(),instance()))[MainViewModel::class.java] }
}