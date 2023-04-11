package com.yasinskyi.android.edu2.di.module.data

import android.content.Context
import androidx.room.Room
import com.yasinskyi.android.edu2.data.local.room.ArticleDao
import com.yasinskyi.android.edu2.data.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "NYTimesNews.db"

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(newsDatabase: NewsDatabase): ArticleDao {
        return newsDatabase.articleDao()
    }
}