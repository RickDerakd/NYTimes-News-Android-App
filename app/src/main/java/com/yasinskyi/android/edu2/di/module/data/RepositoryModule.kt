package com.yasinskyi.android.edu2.di.module.data

import com.yasinskyi.android.edu2.data.NewsRepository
import com.yasinskyi.android.edu2.data.local.NewsLocalDatasource
import com.yasinskyi.android.edu2.data.network.NewsNetworkDatasource
import com.yasinskyi.android.edu2.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepositoryImpl(
        newsLocalDatasource: NewsLocalDatasource,
        newsNetworkDatasource: NewsNetworkDatasource,
    ): NewsRepository {
        return NewsRepositoryImpl(newsLocalDatasource, newsNetworkDatasource)
    }
}