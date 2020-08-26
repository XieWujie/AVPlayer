package com.example.songlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.common.base.AndroidLifeCycleProvide
import com.example.songlist.SongSquareActivity
import com.example.songlist.http.SongSquareApi
import com.example.songlist.repository.SongSquareRepository
import com.example.songlist.vm.SongSquareViewModel
import com.example.songlist.vm.SongSquareViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

private const val SONG_SQUARE_ACTIVITY_MODULE = "song_square_activity_module"

val songSquareModule = Kodein.Module(SONG_SQUARE_ACTIVITY_MODULE) {
    /**
     * API 接口
     */
    bind<SongSquareApi>() with provider {
        instance<Retrofit>().create(SongSquareApi::class.java)
    }

    /**
     * 提供生命周期
     */

    bind<AndroidLifeCycleProvide>() with provider {
        AndroidLifeCycleProvide(instance<SongSquareActivity>())
    }
    /**
     * 歌单广场 歌单标签 category Repository
     */
    bind<SongSquareRepository>() with singleton {
        SongSquareRepository(instance<SongSquareApi>(), instance<AndroidLifeCycleProvide>())
    }

    /**
     * 歌单广场 ViewModel Factory
     */
    bind<SongSquareViewModelFactory>() with provider {
        SongSquareViewModelFactory(
            instance<SongSquareRepository>(),
            instance<AndroidLifeCycleProvide>()
        )
    }
    /**
     * 歌单广场 ViewModel
     */
    bind<SongSquareViewModel>() with provider {
        ViewModelProvider(
            instance<SongSquareActivity>(),
            instance<SongSquareViewModelFactory>()
        )[SongSquareViewModel::class.java]
    }


}