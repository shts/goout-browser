package jp.shts.android.gooutbrowser

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import jp.shts.android.gooutbrowser.data.Article
import jp.shts.android.gooutbrowser.parser.Parser
import jp.shts.android.gooutbrowser.store.ArticleDatabase
import jp.shts.android.gooutbrowser.store.ArticleRepository
import kotlinx.coroutines.experimental.launch
import timber.log.Timber

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private val parser = Parser()

    private val repo by lazy {
        ArticleRepository.getInstance(ArticleDatabase.getInstance(application))
    }

//    private val dataList by lazy {
//        Array(Category.values().size, { MutableLiveData<List<Article>>() })
//    }

//    fun getLiveData(category: Category) = dataList[category.ordinal]

    fun liveData(from: Category): LiveData<List<Article>> {
        return when (from) {
            Category.FASHION -> repo.fashionLiveData
            Category.GEAR -> repo.gearLiveData
            Category.FES_MUSIC -> repo.fesMusicLiveData
            Category.LIVIN -> repo.livinLiveData
            Category.ACTIVITY -> repo.activityLiveData
            Category.CAR -> repo.carLiveData
            Category.TECH -> repo.techLiveData
            Category.INFO -> repo.infoLiveData
        }
    }


    fun get(category: Category) {
        launch {
            try {
                // ローカルDBに保存する
                val data = parse(category)
                repo.insert(data)
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                // DBの記事一覧を返す
//                getLiveData(category).postValue(repo.all(category))
            }
        }
    }

    private fun parse(category: Category): List<Article> {
        return parser.parse(category)
    }

    fun favorite(article: Article) {
        launch {
            article.favorite = true
            repo.update(article)
        }
    }

    fun favToggle(article: Article) {
        launch {
            article.favorite = !article.favorite
            repo.update(article)
        }
    }
}