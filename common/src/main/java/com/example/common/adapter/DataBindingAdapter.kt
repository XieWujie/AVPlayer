package com.example.common.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class DataBindingAdapter {

    companion object{

        @BindingAdapter("img")
        @JvmStatic
        fun img(image:ImageView,url:String){
            Glide.with(image).load(url).into(image)
        }

        @BindingAdapter("img")
        @JvmStatic
        fun img(image:CircleImageView,url:String){
            Glide.with(image).load(url).into(image)
        }
    }
}