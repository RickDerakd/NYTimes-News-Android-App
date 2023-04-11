package com.yasinskyi.android.edu2.di.module.data

import com.yasinskyi.android.edu2.data.local.NewsLocalDatasource
import com.yasinskyi.android.edu2.data.local.room.ArticleDao
import com.yasinskyi.android.edu2.data.mapper.ArticleDboFromDomainMapper
import com.yasinskyi.android.edu2.data.mapper.NewsFromDatabaseMapper
import com.yasinskyi.android.edu2.data.mapper.NewsFromNetworkMapper
import com.yasinskyi.android.edu2.data.network.NewsNetworkDatasource
import com.yasinskyi.android.edu2.data.network.retrofit.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    @Singleton
    fun provideNewsNetworkDatasource(
        apiService: NewsApiService,
        newsFromNetworkMapper: NewsFromNetworkMapper,
    ): NewsNetworkDatasource {
        return NewsNetworkDatasource(apiService, newsFromNetworkMapper)
    }

    @Provides
    @Singleton
    fun provideNewsLocalDatasource(
        articleDao: ArticleDao,
        newsFromDatabaseMapper: NewsFromDatabaseMapper,
        dboFromArticleMapper: ArticleDboFromDomainMapper,
    ): NewsLocalDatasource {
        return NewsLocalDatasource(
            articleDao,
            newsFromDatabaseMapper,
            dboFromArticleMapper,
        )
    }
}