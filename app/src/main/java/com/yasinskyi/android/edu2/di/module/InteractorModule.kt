package com.yasinskyi.android.edu2.di.module

import com.yasinskyi.android.edu2.mvvm.interactor.NewsValidationInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun provideNewsValidationInteractor(): NewsValidationInteractor {
        return NewsValidationInteractor()
    }
}