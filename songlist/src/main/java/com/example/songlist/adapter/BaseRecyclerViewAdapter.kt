package com.example.songlist.adapter

import android.content.Context
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    private val layoutId: Int,
    protected var list: ArrayList<T>
) :
    RecyclerView.Adapter<ViewHolder>() {
    lateinit var context: Context
    var itemClickListener: ((View, T, Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(context, parent, layoutId)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itemClickListener?.let {
            holder.itemView.setOnClickListener { it(holder.itemView, list[position], position) }
        }
        bindViewHolder(holder, list[position], position)
    }

    override fun getItemCount(): Int = getCount()

    open fun getCount(): Int {
        Log.e(TAG, list.size.toString())
        return list.size
    }

    /**
     * 设置数据 全局刷新
     */
    fun setData(data: ArrayList<T>) {
        if (data.isEmpty()) {
            return
        }

        list = data
        notifyDataSetChanged()
    }

    /**
     * 添加数据 局部范围刷新
     */
    fun addData(data: ArrayList<T>) {
        if (data.isEmpty()) {
            return
        }

        val size = list.size
        list.addAll(data)
        notifyItemRangeInserted(size, data.size)
    }

    /**
     * 添加某一项数据 某位置刷新
     */
    fun addItem(data: T) {
        val size = list.size
        list.add(data)
        notifyItemInserted(size)
    }

    /**
     * 清空数据 list
     */
    fun clear() {
        list.clear()
    }

    abstract fun bindViewHolder(holder: ViewHolder, item: T, position: Int)

    private val TAG = this.javaClass.simpleName
}