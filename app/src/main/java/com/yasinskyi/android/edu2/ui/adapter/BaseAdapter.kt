package com.yasinskyi.android.edu2.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T : Any>(
    diffUtil: DiffUtil.ItemCallback<T>,
) : ListAdapter<T, BaseViewHolder<T, *>>(diffUtil) {

    override fun onBindViewHolder(holder: BaseViewHolder<T, *>, position: Int) {
        holder.onBind(currentList[position])
    }
}