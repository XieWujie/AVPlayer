package com.example.songlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.common.base.AndroidLifeCycleProvide
import com.example.songlist.SongSquareActivity
import com.example.songlist.http.SongSquareApi
import com.example.songlist.repository.SongSquareFragmentRepository
import com.example.songlist.vm.SongListViewModel
import com.example.songlist.vm.SongListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

private const val SONG_LIST_MODULE = "song_list_module"

val songListModel = Kodein.Module(SONG_LIST_MODULE) {
    /**
     * 歌单广场 所有fragment Repository
     */
    bind<SongSquareFragmentRepository>() with singleton {
        SongSquareFragmentRepository(instance<SongSquareApi>(), instance<AndroidLifeCycleProvide>())
    }

    bind<SongListViewModelFactory>() with provider {
        SongListViewModelFactory(
            instance<SongSquareFragmentRepository>(),
            instance<AndroidLifeCycleProvide>()
        )
    }

    bind<SongListViewModel>() with provider {
        ViewModelProvider(
            instance<SongSquareActivity>(),
            instance<SongListViewModelFactory>()
        )[SongListViewModel::class.java]
    }
}