package jp.shts.android.gooutbrowser.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import jp.shts.android.gooutbrowser.store.ArticleDao

@Entity(tableName = ArticleDao.TABLE_NAME)
data class Article(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "link")
        val link: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "summary")
        val summary: String,
        @ColumnInfo(name = "date")
        val date: Long,
        @ColumnInfo(name = "date_text")
        val dateText: String,
        @ColumnInfo(name = "thumb")
        val thumb: String,
        @ColumnInfo(name = "category")
        val category: String,
        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false
)
