package com.yasinskyi.android.edu2.data.network.retrofit

import com.yasinskyi.android.edu2.BuildConfig
import com.yasinskyi.android.edu2.data.network.entity.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/svc/news/v3/content/nyt/all.json")
    suspend fun fetchNews(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("api-key") apiKey: String = BuildConfig.API_KEY,
    ): NewsResponse
}