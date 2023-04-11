package com.yasinskyi.android.edu2.ui.adapter.news

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yasinskyi.android.edu2.R
import com.yasinskyi.android.edu2.databinding.NewsShortItemBinding
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.ui.adapter.BaseViewHolder
import com.yasinskyi.android.edu2.util.DateFormatter

class ShortNewsViewHolder(
    parent: ViewGroup,
    private val onLinkClickListener: (String) -> Unit,
) : BaseViewHolder<Article, NewsShortItemBinding>(parent, R.layout.news_short_item, NewsShortItemBinding::bind) {

    override fun onBind(item: Article) {
        super.onBind(item)

        binding.apply {
            newsItemTitle.text = item.title
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

