package jp.shts.android.gooutbrowser.store

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import jp.shts.android.gooutbrowser.Category
import jp.shts.android.gooutbrowser.data.Article

@Dao
interface ArticleDao {

    companion object {
        const val TABLE_NAME = "articles"
    }

    @Insert
    fun insert(data: Article)

    @Update
    fun update(data: Article)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    fun article(id: String): Article?

    @Query("SELECT * FROM $TABLE_NAME WHERE category = :category")
    fun all(category: String): List<Article>

    // -------------------------------------------------------------
    // 各カテゴリのクエリ
    // -------------------------------------------------------------
    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'FASHION'")
    fun fashion(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'GEAR'")
    fun gear(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'FES_MUSIC'")
    fun fesMusic(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'LIVIN'")
    fun livin(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'ACTIVITY'")
    fun activity(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'TECH'")
    fun tech(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'CAR'")
    fun car(): LiveData<List<Article>>

    @Query("SELECT * FROM $TABLE_NAME WHERE category = 'INFO'")
    fun info(): LiveData<List<Article>>

}
