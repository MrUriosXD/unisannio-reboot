package solutions.alterego.android.unisannio.core.net

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Test
import solutions.alterego.android.unisannio.core.Failure
import solutions.alterego.android.unisannio.core.Success

@Suppress("RemoveRedundantBackticks")
class ElementsRetrieverTest {
    @Test
    fun `Try to fetch Gurisprudenza RSS`() = runBlocking {
        val retriever = ElementsRetriever("http://www.dstunisannio.it/index.php/didattica?format=feed&type=rss", "item")
        val elements = retriever.fetchItems()
        Assert.assertTrue(elements is Success)
    }

    @Test
    fun `Fail to fetch Gurisprudenza RSS`() = runBlocking {
        val retriever = ElementsRetriever("http://www.dstunisannio.it/index.php/didattica", "item")
        val elements = retriever.fetchItems()
        Assert.assertTrue(elements is Failure)
    }
}
