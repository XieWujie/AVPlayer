package com.example.main.adapter.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.R
import java.lang.RuntimeException

class MyMusicAdapter :RecyclerView.Adapter<MyMusicAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view =  when(viewType){
            MY_LOVE ->{
               val view =  LayoutInflater.from(parent.context).inflate(R.layout.my_love_layout,parent,false)
                view.setOnClickListener {

                }
                view
            }
           else->throw RuntimeException("item type error")
        }
        return ViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun getItemViewType(position: Int) =
        when(position){
            0-> MY_LOVE
            else->throw RuntimeException("item type error")
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){


    }

    companion object{
        const val MY_LOVE = 0
    }
}