package com.yasinskyi.android.edu2.mvvm.entity

data class Article(
    val publishedDate: Long,
    val url: String,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
)
