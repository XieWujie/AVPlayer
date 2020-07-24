package com.example.main.di

import androidx.lifecycle.ViewModelProvider
import com.example.main.MainActivity
import com.example.main.adapter.mine.SongListAdapter
import com.example.main.http.MineApi
import com.example.main.repository.IMineIRepository
import com.example.main.repository.MineLocal
import com.example.main.repository.MineRepository
import com.example.main.viewmodel.MineViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit

private const val MINE_FRAGMENT_MODULE = "mine_fragment_module"

val mine_fragment = Kodein.Module(MINE_FRAGMENT_MODULE){
    bind<MineApi>() with provider {
        instance<Retrofit>().create(MineApi::class.java)
    }
  bind<MineLocal>() with provider {
      MineLocal(instance())
  }
//    bind<AndroidLifeCycleProvide>() with provider {
//        AndroidLifeCycleProvide(instance<MineFragment>())
//    }

    bind<SongListAdapter>() with provider {
        SongListAdapter()
    }
    bind<IMineIRepository>() with provider {
        MineRepository(instance(),instance(),instance())
    }
    bind<MineViewModel>() with provider {
        ViewModelProvider(instance<MainActivity>(),MineViewModel.factory(instance<IMineIRepository>(),instance()))[MineViewModel::class.java]
    }
}