package com.yasinskyi.android.edu2.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.yasinskyi.android.edu2.util.extension.inflateView

abstract class BaseViewHolder<T : Any, VB : ViewBinding>(
    parent: ViewGroup,
    @LayoutRes layoutRes: Int,
    viewBindingProvider: (View) -> VB,
) : RecyclerView.ViewHolder(parent.inflateView(layoutRes)) {

    protected val binding: VB =  viewBindingProvider(itemView)

    protected val context: Context get() = itemView.context

    protected lateinit var item: T
        private set

    @CallSuper open fun onBind(item: T) {
        this.item = item
    }
}