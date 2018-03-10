package solutions.alterego.android.unisannio.ateneo

import org.jsoup.nodes.Document
import rx.Observable
import solutions.alterego.android.unisannio.interfaces.Parser
import solutions.alterego.android.unisannio.interfaces.IRetriever
import solutions.alterego.android.unisannio.core.Article

class AteneoPresenter(url: String) : IAvvisiPresenter {

    private val mParser: Parser<Article>

    private val retriever: IRetriever<Document>

    init {
        mParser = AteneoAvvisiParser()
        retriever = AteneoRetriever(url)
    }

    override fun getArticles(): Observable<List<Article>> {
        return retriever.retrieveDocument()
            .map { document -> mParser.parse(document) }
    }
}
