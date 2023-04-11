package com.yasinskyi.android.edu2.data.network

import com.yasinskyi.android.edu2.data.mapper.NewsFromNetworkMapper
import com.yasinskyi.android.edu2.data.network.retrofit.NewsApiService
import com.yasinskyi.android.edu2.mvvm.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsNetworkDatasource(
    private val newsApiService: NewsApiService,
    private val newsFromNetworkMapper: NewsFromNetworkMapper,
) {

    fun getArticles(limit: Int, offset: Int): Flow<List<Article>> = flow {
        emit(
            newsApiService
                .fetchNews(limit, offset)
                .run(newsFromNetworkMapper)
        )
    }
}