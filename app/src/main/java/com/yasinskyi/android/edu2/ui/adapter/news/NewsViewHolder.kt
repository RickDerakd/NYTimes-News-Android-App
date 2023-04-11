package com.yasinskyi.android.edu2.ui.adapter.news

import android.view.ViewGroup
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yasinskyi.android.edu2.R
import com.yasinskyi.android.edu2.databinding.NewsItemBinding
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.ui.adapter.BaseViewHolder
import com.yasinskyi.android.edu2.util.DateFormatter

class NewsViewHolder(
    parent: ViewGroup,
    private val onLinkClickListener: (String) -> Unit,
) : BaseViewHolder<Article, NewsItemBinding>(parent, R.layout.news_item, NewsItemBinding::bind) {

    override fun onBind(item: Article) {
        super.onBind(item)

        binding.apply {
            newsItemTitle.text = item.title

            newsItemDescription.isGone = item.description.isNullOrEmpty()
            newsItemDescription.text = item.description

            Glide
                .with(context)
                .load(item.imageUrl)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_placeholder_image)
                .transform(
                    CenterCrop(),
                    RoundedCorners(context.resources.getDimensionPixelSize(R.dimen.cornerRadius)),
                )
                .into(newsItemImage)

            root.setOnClickListener {
                onLinkClickListener(item.url)
            }

            newsItemDate.text = DateFormatter.formatLongToTime(item.publishedDate)
        }
    }
}

