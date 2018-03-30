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
        try {
            val articles = elements
                .map { item ->
                    // If no ID is present in the XML, we generate a UUID for the sake of having an ID
                    val id = elements.select("guid")?.first()?.text()
                        .takeUnless { it.isNullOrEmpty() } ?: UUID.randomUUID().toString()

                    val url = item.select("link")?.first()?.text() ?: ""
                    val description = item.select("description")?.first()?.text()

                    val body = if (description.isNullOrEmpty() || description == "<![CDATA[]]>") url else description

                    Article(
                        id = id,
                        title = item.select("title")?.first()?.text() ?: "",
                        author = item.select("author")?.first()?.text() ?: "",
                        url = url,
                        body = body ?: "",
                        date = item.select("pubDate")?.first()?.text() ?: "",
                        source = item.select("source")?.first()?.text() ?: ""
                    )
                }

            if (articles.isEmpty()) it.resume(Failure(Throwable("Error parsing elements")))
            else it.resume(Success(articles))
        } catch (e: Exception) {
            it.resume(Failure(e))
        }
    }
}
