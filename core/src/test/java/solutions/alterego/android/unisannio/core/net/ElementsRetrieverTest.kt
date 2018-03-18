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
        val retriever = ElementsRetriever()
        val elements = retriever.fetchItems("http://www.dstunisannio.it/index.php/didattica?format=feed&type=rss", "item")
        Assert.assertTrue(elements is Success)
    }

    @Test
    fun `Fail to fetch Gurisprudenza RSS`() = runBlocking {
        val retriever = ElementsRetriever()
        val elements = retriever.fetchItems("http://www.dstunisannio.it/index.php/didattica", "item")
        Assert.assertTrue(elements is Failure)
    }
}
