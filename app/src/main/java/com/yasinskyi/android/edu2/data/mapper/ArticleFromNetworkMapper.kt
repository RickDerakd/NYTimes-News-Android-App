package com.yasinskyi.android.edu2.data.mapper

import com.yasinskyi.android.edu2.data.network.entity.ArticleResponse
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.data.network.entity.ImageFormat
import com.yasinskyi.android.edu2.util.extension.toMills

class ArticleFromNetworkMapper : BaseMapper<ArticleResponse, Article> {

    override operator fun invoke(item: ArticleResponse): Article {
        val imageUrl = item.imageUrl
            ?.findLast { it.format == ImageFormat.THUMBNAIL }
            ?.imageUrl

        return Article(
            publishedDate = item.publishedDate.toMills(),
            url = item.url,
            title = item.title,
            description = item.description,
            imageUrl = imageUrl
        )
    }
}