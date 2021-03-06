package com.example.songlist.adapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.songlist.extentions.gone
import com.example.songlist.extentions.load
import com.example.songlist.extentions.visible

class ViewHolder(context: Context, parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false)) {

    private val views = SparseArray<View>()

    private fun <T : View> getView(viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            if (view == null) {
                throw IllegalArgumentException("not found id")
            }
            views.put(viewId, view)
        }

        return view as T
    }

    fun setText(@IdRes viewId: Int, text: CharSequence): ViewHolder {
        val textView: TextView = getView(viewId)
        textView.text = text
        return this
    }

    fun setImageResource(@IdRes viewId: Int, @DrawableRes resId: Int): ViewHolder {
        val imageView: ImageView = getView(viewId)
        imageView.setImageResource(resId)
        return this
    }

    fun setImage(@IdRes viewId: Int, url: String): ViewHolder {
        val imageView: ImageView = getView(viewId)
        imageView.load(url)
        return this
    }

    fun gone(@IdRes viewId: Int): ViewHolder {
        val view: View = getView(viewId)
        view.gone()
        return this
    }

    fun gone(view: View): ViewHolder {
        view.gone()
        return this
    }

    fun visible(@IdRes viewId: Int): ViewHolder {
        val view: View = getView(viewId)
        view.visible()
        return this
    }

    fun visible(view: View): ViewHolder {
        view.visible()
        return this
    }

    fun setClickListener(@IdRes viewId: Int, f: (View) -> Unit): ViewHolder {
        getView<View>(viewId).apply {
            setOnClickListener { f(this) }
        }
        return this
    }

    fun setClickListener(viewIds: Array<Int>, f: (View) -> Unit): ViewHolder {
        viewIds.forEach {
            getView<View>(it).apply {
                setOnClickListener { f(this) }
            }
        }
        return this
    }
}