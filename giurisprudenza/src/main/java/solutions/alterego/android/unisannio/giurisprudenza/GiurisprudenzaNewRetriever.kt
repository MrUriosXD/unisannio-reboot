package solutions.alterego.android.unisannio.giurisprudenza

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import solutions.alterego.android.unisannio.core.Article
import java.util.UUID
import javax.inject.Inject

class GiurisprudenzaNewRetriever @Inject constructor() {

    private val rssUrl = "http://www.giurisprudenza.unisannio.it/index.php?option=com_rss&catid=2"

    fun fetchItems(): Elements {
        return Jsoup.connect(rssUrl)
            .timeout(10 * 1000)
            .parser(org.jsoup.parser.Parser.xmlParser())
            .get()
            .select("item")
    }

    fun parseItems(elements: Elements): List<Article> {
        return elements
            .map {
                Article(
                    UUID.randomUUID().toString(),
                    elements.select("title").text(),
                    elements.select("author").text(),
                    elements.select("link").text(),
                    elements.select("description").text(),
                    ""
                )
            }
    }
}
