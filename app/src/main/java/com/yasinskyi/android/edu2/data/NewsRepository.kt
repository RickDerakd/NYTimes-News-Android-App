package com.yasinskyi.android.edu2.data

import com.yasinskyi.android.edu2.mvvm.entity.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun insert(article: List<Article>)

    suspend fun delete(article: List<Article>)

    fun getArticles(limit: Int, offset: Int): Flow<List<Article>>
}