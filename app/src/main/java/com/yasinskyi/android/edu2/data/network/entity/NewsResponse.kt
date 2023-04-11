package com.yasinskyi.android.edu2.data.network.entity

import com.google.gson.annotations.SerializedName
import com.yasinskyi.android.edu2.data.network.entity.ArticleResponse

data class NewsResponse(
    @SerializedName("results")
    val news: List<ArticleResponse>,
)
