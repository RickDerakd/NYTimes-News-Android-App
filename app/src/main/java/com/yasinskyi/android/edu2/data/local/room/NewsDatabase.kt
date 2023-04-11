package com.yasinskyi.android.edu2.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yasinskyi.android.edu2.data.local.entity.ArticleDbo

@Database(
    entities = [ArticleDbo::class],
    version = 1,
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}