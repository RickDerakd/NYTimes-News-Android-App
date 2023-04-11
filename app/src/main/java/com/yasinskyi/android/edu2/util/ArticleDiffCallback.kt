package com.yasinskyi.android.edu2.util

import androidx.recyclerview.widget.DiffUtil
import com.yasinskyi.android.edu2.mvvm.entity.Article

object ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
