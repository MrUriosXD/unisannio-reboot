package solutions.alterego.android.unisannio.core

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Test
import solutions.alterego.android.unisannio.core.net.ElementsRetriever

@Suppress("RemoveRedundantBackticks")
class ArticleParserTest {
    @Test
    fun `Parse article list from Giurisprudenza retrieved elements list`() = runBlocking {
        val retriever = ElementsRetriever("http://www.dstunisannio.it/index.php/didattica?format=feed&type=rss", "item")
        val result = retriever.fetchItems()

        val resultRetrieving = result
            .onSuccess { elements ->
                val parser = ArticleParser()
                val parsingResult = parser.parse(elements)

                Assert.assertTrue(parsingResult is Success)
            }
    }
}
