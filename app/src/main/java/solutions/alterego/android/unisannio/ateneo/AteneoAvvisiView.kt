package solutions.alterego.android.unisannio.ateneo

import solutions.alterego.android.unisannio.core.Article

interface AteneoAvvisiView {

    fun stopProgress()

    fun setNews(articles: List<Article>)
}
