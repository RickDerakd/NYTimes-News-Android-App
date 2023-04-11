package com.yasinskyi.android.edu2.data.mapper

import com.yasinskyi.android.edu2.data.network.entity.NewsResponse
import com.yasinskyi.android.edu2.mvvm.entity.Article

class NewsFromNetworkMapper(
    private val articleFromNetworkMapper: ArticleFromNetworkMapper,
) : BaseMapper<NewsResponse, List<Article>> {

    override operator fun invoke(item: NewsResponse) =
        item
            .news
            .map(articleFromNetworkMapper)
            .toList()
}