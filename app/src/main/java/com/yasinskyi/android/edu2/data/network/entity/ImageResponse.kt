package com.yasinskyi.android.edu2.data.network.entity

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("url")
    val imageUrl: String?,

    @SerializedName("format")
    val format: ImageFormat?,
)