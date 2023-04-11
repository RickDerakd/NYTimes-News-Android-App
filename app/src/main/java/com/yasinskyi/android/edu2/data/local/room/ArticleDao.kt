package com.yasinskyi.android.edu2.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yasinskyi.android.edu2.data.local.entity.ArticleDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleDbo: List<ArticleDbo>)

    @Delete
    suspend fun delete(articleDbo: List<ArticleDbo>)

    @Query("SELECT * FROM articleDbo ORDER BY published_date DESC LIMIT :limit OFFSET :offset")
    fun getArticles(limit: Int, offset: Int): Flow<List<ArticleDbo>>
}