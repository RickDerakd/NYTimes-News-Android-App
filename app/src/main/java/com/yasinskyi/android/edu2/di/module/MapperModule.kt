package com.yasinskyi.android.edu2.di.module

import com.yasinskyi.android.edu2.data.mapper.ArticleFromDatabaseMapper
import com.yasinskyi.android.edu2.data.mapper.ArticleFromNetworkMapper
import com.yasinskyi.android.edu2.data.mapper.ArticleDboFromDomainMapper
import com.yasinskyi.android.edu2.data.mapper.NewsFromNetworkMapper
import com.yasinskyi.android.edu2.data.mapper.NewsFromDatabaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideArticleFromNetworkMapper(): ArticleFromNetworkMapper {
        return ArticleFromNetworkMapper()
    }

    @Provides
    @Singleton
    fun provideArticleFromDatabaseMapper(): ArticleFromDatabaseMapper {
        return ArticleFromDatabaseMapper()
    }

    @Provides
    @Singleton
    fun provideArticleDboFromDomainMapper(): ArticleDboFromDomainMapper {
        return ArticleDboFromDomainMapper()
    }

    @Provides
    @Singleton
    fun provideNewsFromNetworkMapper(
        articleFromNetworkMapper: ArticleFromNetworkMapper,
    ): NewsFromNetworkMapper {
        return NewsFromNetworkMapper(articleFromNetworkMapper)
    }

    @Provides
    @Singleton
    fun provideNewsFromDatabaseMapper(
        articleFromDatabaseMapper: ArticleFromDatabaseMapper,
    ): NewsFromDatabaseMapper {
        return NewsFromDatabaseMapper(articleFromDatabaseMapper)
    }
}