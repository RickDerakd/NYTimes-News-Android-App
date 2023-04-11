package com.yasinskyi.android.edu2.di.module.network

import android.content.Context
import com.google.gson.Gson
import com.yasinskyi.android.edu2.BuildConfig
import com.yasinskyi.android.edu2.data.network.retrofit.NetworkInterceptor
import com.yasinskyi.android.edu2.data.network.retrofit.NewsApiService
import com.yasinskyi.android.edu2.data.network.retrofit.OkHttpLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkInterceptor: NetworkInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkInterceptor {
        return NetworkInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(OkHttpLogger())
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
    }
}