package jp.shts.android.gooutbrowser.parser

import jp.shts.android.gooutbrowser.Category
import jp.shts.android.gooutbrowser.data.Article
import org.jsoup.Jsoup
import org.threeten.bp.ZonedDateTime

class Parser {

    fun parse(category: Category): List<Article> {
        return Jsoup.connect(category.url())
                .userAgent(UserAgent.get())
                .get()
                .body()
                .select("article").mapNotNull {
                    // post-を削除して整数で保存しようと思ったけどやめた
                    val id = it.attr("id")
                    val link = it.selectFirst("a").attr("href")
                    val title = it.selectFirst(".entry-title")?.text() ?: return@mapNotNull null
                    val summary = it.selectFirst(".tx-summary")?.text() ?: return@mapNotNull null
                    val date = it.selectFirst("time")?.attr("datetime")?.let {
                        ZonedDateTime.parse(it).toInstant().toEpochMilli()
                    } ?: return@mapNotNull null
                    val dateText = it.selectFirst("time")?.text() ?: return@mapNotNull null
                    val thumb = it.selectFirst(".img-eyecatch")?.selectFirst("img")?.attr("src")
                            ?: return@mapNotNull null
                    return@mapNotNull Article(
                            id,
                            link,
                            title,
                            summary,
                            date,
                            dateText,
                            thumb,
                            category.name
                    )
                }
    }
}