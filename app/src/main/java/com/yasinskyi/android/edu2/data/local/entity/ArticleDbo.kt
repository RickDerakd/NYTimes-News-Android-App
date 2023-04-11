package com.yasinskyi.android.edu2.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleDbo(
    @PrimaryKey
    @ColumnInfo(name = "published_date")
    val publishedDate: Long,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
)