package com.yasinskyi.android.edu2.data.local

import com.yasinskyi.android.edu2.data.local.room.ArticleDao
import com.yasinskyi.android.edu2.data.mapper.ArticleDboFromDomainMapper
import com.yasinskyi.android.edu2.data.mapper.NewsFromDatabaseMapper
import com.yasinskyi.android.edu2.mvvm.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsLocalDatasource(
    private val articleDao: ArticleDao,
    private val newsFromDatabaseMapper: NewsFromDatabaseMapper,
    private val articleDboFromDomainMapper: ArticleDboFromDomainMapper,
) {

    suspend fun insert(articleList: List<Article>) {
        articleDao
            .insert(articleList.map(articleDboFromDomainMapper))
    }

    suspend fun delete(articleList: List<Article>) {
        articleDao
            .delete(articleList.map(articleDboFromDomainMapper))
    }

    fun getArticles(limit: Int, offset: Int): Flow<List<Article>> =
        articleDao
            .getArticles(limit, offset)
            .map(newsFromDatabaseMapper)
}
