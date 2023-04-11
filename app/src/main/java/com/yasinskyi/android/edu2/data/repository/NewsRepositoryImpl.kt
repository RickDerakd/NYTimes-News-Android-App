package com.yasinskyi.android.edu2.data.repository

import com.yasinskyi.android.edu2.data.NewsRepository
import com.yasinskyi.android.edu2.data.exception.InternetConnectionException
import com.yasinskyi.android.edu2.data.local.NewsLocalDatasource
import com.yasinskyi.android.edu2.data.network.NewsNetworkDatasource
import com.yasinskyi.android.edu2.mvvm.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsLocalDatasource: NewsLocalDatasource,
    private val newsNetworkDatasource: NewsNetworkDatasource,
) : NewsRepository {

    override suspend fun insert(article: List<Article>) {
        newsLocalDatasource.insert(article)
    }

    override suspend fun delete(article: List<Article>) {
        newsLocalDatasource.delete(article)
    }

    override fun getArticles(limit: Int, offset: Int): Flow<List<Article>> {
        return newsNetworkDatasource.getArticles(limit, offset)
            .onEach { insert(it) }
            .catch { exception ->
                if (exception is InternetConnectionException) {
                    emit(newsLocalDatasource.getArticles(limit, offset).first())
                }
            }
    }
}
