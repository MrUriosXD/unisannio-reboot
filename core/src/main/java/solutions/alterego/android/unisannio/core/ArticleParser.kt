package solutions.alterego.android.unisannio.core

import org.jsoup.select.Elements
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.experimental.suspendCoroutine

interface Parser {
    suspend fun parse(elements: Elements): Result<List<Article>>
}

class ArticleParser @Inject constructor() : Parser {
    override suspend fun parse(elements: Elements): Result<List<Article>> = suspendCoroutine {
        val articles = elements
            .map {
                // If no ID is present in the XML, we generate a UUID for the sake of having an ID
                val id = elements.select("guid").text()
                    .takeUnless { it.isNullOrEmpty() } ?: UUID.randomUUID().toString()

                Article(
                    id = id,
                    title = elements.select("title").text(),
                    author = elements.select("author").text(),
                    url = elements.select("link").text(),
                    body = elements.select("description").text(),
                    date = elements.select("pubDate").text(),
                    source = elements.select("source").text()
                )
            }

        if (articles.isEmpty()) it.resume(Failure(Throwable("Error parsing elements")))
        else it.resume(Success(articles))
    }
}
