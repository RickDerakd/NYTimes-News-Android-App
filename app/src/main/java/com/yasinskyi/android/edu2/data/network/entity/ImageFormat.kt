package com.yasinskyi.android.edu2.data.network.entity

import com.google.gson.annotations.SerializedName

enum class ImageFormat {
    @SerializedName("Standard Thumbnail")
    THUMBNAIL,

    @SerializedName("Normal")
    NORMAL,

    @SerializedName("mediumThreeByTwo210")
    MEDIUM,

    @SerializedName("mediumThreeByTwo440")
    LARGE,
}