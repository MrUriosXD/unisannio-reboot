package solutions.alterego.android.unisannio.core.net

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import solutions.alterego.android.unisannio.core.Failure
import solutions.alterego.android.unisannio.core.Result
import solutions.alterego.android.unisannio.core.Success
import javax.inject.Inject
import kotlin.coroutines.experimental.suspendCoroutine

class ElementsRetriever @Inject constructor() {
    suspend fun fetchItems(url: String, itemTag: String): Result<Elements> = suspendCoroutine {
        try {
            val elements = Jsoup.connect(url)
                .timeout(10 * 1000)
                .parser(org.jsoup.parser.Parser.xmlParser())
                .get()
                .select(itemTag)

            when {
                elements.isNotEmpty() -> it.resume(Success(elements))
                else -> it.resume(Failure(Throwable("Failed to parse items")))
            }
        } catch (e: Exception) {
            it.resume(Failure(e))
        }
    }
}
