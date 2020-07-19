package com.example.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.conmon.Account
import com.example.conmon.base.AVFragment
import com.example.conmon.extension.bind
import com.example.conmon.extension.toast
import com.example.main.R
import com.example.main.databinding.FragmentMineBinding
import com.example.main.di.mine_fragment
import com.example.main.viewmodel.MineViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

/**
 * 主页下我的 tab下的UI
 */
class MineFragment :AVFragment<MineViewModel>(),KodeinAware{

    override val kodein = Kodein.lazy {
        extend(parent)
        import(mine_fragment)
        bind<MineFragment>() with scoped(AndroidLifecycleScope).singleton{ this@MineFragment }
    }
    private lateinit var binding:FragmentMineBinding
    override val viewModel:MineViewModel by instance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(R.layout.fragment_mine,container)
        dispatchEvent()
        initView()
        return binding.root
    }

    private fun initView(){
        Glide.with(this).load(Account.avatarUrl).into(binding.userAvatarView)
        binding.username.text = Account.nickname
    }

    private fun dispatchEvent(){
        error.observe(this, Observer {
            context?.toast(it.message?:"")
        })
        viewModel.subCount().observe(this, Observer {
            binding.createSongListCountText.text = "${it.createDjRadioCount}"
            binding.collectionSongListCountTextview.text = "${it.subPlaylistCount}"
        })
        viewModel.played().observe(this, Observer {
            binding.playedCountTextview.text = "${it.weekData.size}"
            if(it.weekData.isNotEmpty()) {
                Glide.with(this).load(it.weekData[0].song.al.picUrl).into(binding.latestSongImgView)
            }
        })
    }

}