package com.yasinskyi.android.edu2.data.mapper

import com.yasinskyi.android.edu2.data.local.entity.ArticleDbo
import com.yasinskyi.android.edu2.mvvm.entity.Article

class ArticleDboFromDomainMapper : BaseMapper<Article, ArticleDbo> {

    override fun invoke(item: Article): ArticleDbo =
        ArticleDbo(
            publishedDate = item.publishedDate,
            url = item.url,
            title = item.title,
            description = item.description,
            imageUrl = item.imageUrl,
        )
}