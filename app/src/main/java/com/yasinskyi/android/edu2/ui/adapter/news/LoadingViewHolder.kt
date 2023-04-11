package com.yasinskyi.android.edu2.ui.adapter.news

import android.view.ViewGroup
import com.yasinskyi.android.edu2.R
import com.yasinskyi.android.edu2.databinding.LoadingItemBinding
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.ui.adapter.BaseViewHolder

class LoadingViewHolder(
    parent: ViewGroup,
) : BaseViewHolder<Article, LoadingItemBinding>(parent, R.layout.loading_item, LoadingItemBinding::bind) {

    override fun onBind(item: Article) {
        super.onBind(item)
        binding.progressSpinner.show()
    }
}