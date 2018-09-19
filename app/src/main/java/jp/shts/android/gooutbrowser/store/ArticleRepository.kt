package jp.shts.android.gooutbrowser.store

import jp.shts.android.gooutbrowser.Category
import jp.shts.android.gooutbrowser.data.Article
import timber.log.Timber

class ArticleRepository(
        private val database: ArticleDatabase
) {

    companion object {
        @Volatile
        private var INSTANCE: ArticleRepository? = null

        fun getInstance(database: ArticleDatabase): ArticleRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: ArticleRepository(database).also { INSTANCE = it }
                }
    }

    private val dao = database.articleDao()

    val fashionLiveData = dao.fashion()
    val gearLiveData = dao.gear()
    val fesMusicLiveData = dao.fesMusic()
    val livinLiveData = dao.livin()
    val activityLiveData = dao.activity()
    val techLiveData = dao.tech()
    val carLiveData = dao.car()
    val infoLiveData = dao.info()

//    fun all(category: Category) = dao.all(category.name)

    fun insert(articles: List<Article>) {
        articles.map {
            try {
                if (isNew(it)) {
                    dao.insert(it)
                }
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }

    fun update(article: Article) {
        dao.update(article)
    }

    private fun isNew(article: Article) = !exist(article.id)

    private fun exist(id: String) = dao.article(id) != null
}