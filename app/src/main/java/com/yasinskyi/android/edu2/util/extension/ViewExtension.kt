package com.yasinskyi.android.edu2.util.extension

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

inline val View.layoutInflater get() = context.layoutInflater

fun ViewGroup.inflateView(
    @LayoutRes layoutId: Int,
    parent: ViewGroup? = this,
    attachToRoot: Boolean = false
): View = layoutInflater.inflate(layoutId, parent, attachToRoot)