package com.yasinskyi.android.edu2.data.mapper

import com.yasinskyi.android.edu2.data.local.entity.ArticleDbo
import com.yasinskyi.android.edu2.mvvm.entity.Article

class NewsFromDatabaseMapper(
    private val articleFromDatabaseMapper: ArticleFromDatabaseMapper,
) : BaseMapper<List<ArticleDbo>, List<Article>> {

    override fun invoke(item: List<ArticleDbo>): List<Article> =
        item.map(articleFromDatabaseMapper)
}