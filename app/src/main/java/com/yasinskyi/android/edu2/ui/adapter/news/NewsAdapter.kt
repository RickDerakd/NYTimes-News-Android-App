package com.yasinskyi.android.edu2.ui.adapter.news

import android.view.ViewGroup
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.ui.adapter.BaseAdapter
import com.yasinskyi.android.edu2.ui.adapter.BaseViewHolder
import com.yasinskyi.android.edu2.util.ArticleDiffCallback

class NewsAdapter(
    private val onLinkClickListener: (String) -> Unit,
) : BaseAdapter<Article>(ArticleDiffCallback) {

    private val emptyArticle = Article(-1, "", null, null, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Article, *> {

        return when (viewType) {
            VIEW_TYPE_LOADING -> LoadingViewHolder(parent)
            VIEW_TYPE_NEWS -> NewsViewHolder(parent, onLinkClickListener)
            VIEW_TYPE_SHORT_NEWS -> ShortNewsViewHolder(parent, onLinkClickListener)
            else -> throw IllegalStateException("Illegal view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val article = currentList[position]

        return when {
            (article == emptyArticle) -> VIEW_TYPE_LOADING
            ((article.description?.length ?: 0) < SHORT_TITLE) -> VIEW_TYPE_SHORT_NEWS
            else -> VIEW_TYPE_NEWS
        }
    }

    fun showLoading() {
        val news = currentList.toMutableList()

        if (!currentList.contains(emptyArticle) && currentList.isNotEmpty()) {
            news.add(emptyArticle)
            submitList(news)
        }
    }

    fun hideLoading() {
        val news = currentList.toMutableList()

        if (currentList.contains(emptyArticle)) {
            news.remove(emptyArticle)
            submitList(news)
        }
    }

    companion object {

        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NEWS = 1
        private const val VIEW_TYPE_SHORT_NEWS = 2

        private const val SHORT_TITLE = 25
    }
}