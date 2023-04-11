package com.yasinskyi.android.edu2.data.network.entity

import com.google.gson.annotations.SerializedName
import com.yasinskyi.android.edu2.data.network.entity.ImageResponse

data class ArticleResponse(
    @SerializedName("published_date")
    val publishedDate: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("title")
    val title: String?,

    @SerializedName("abstract")
    val description: String?,

    @SerializedName("multimedia")
    val imageUrl: List<ImageResponse>?,
)
