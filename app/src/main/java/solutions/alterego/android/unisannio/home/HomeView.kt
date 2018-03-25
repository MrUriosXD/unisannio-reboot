package solutions.alterego.android.unisannio.home

import solutions.alterego.android.unisannio.core.Article

interface HomeView {
    fun setTitleToGiurisprudenza()

    fun showProgressbar()
    fun hideProgressbar()

    fun clearList()
    fun addArticles(articles: List<Article>)

}
