package com.yasinskyi.android.edu2.util.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.isScrolledToEnd(pageSize: Int): Boolean {
    val linearLayoutManager = layoutManager as? LinearLayoutManager ?: return false

    val visibleItemCount = linearLayoutManager.childCount
    val totalItemCount = linearLayoutManager.itemCount
    val firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()

    if (visibleItemCount + firstVisibleItemPosition < totalItemCount) return false
    if (firstVisibleItemPosition < 0) return false

    return totalItemCount >= pageSize
}