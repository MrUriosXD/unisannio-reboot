package solutions.alterego.android.unisannio.home

import solutions.alterego.android.unisannio.core.Article
import solutions.alterego.android.unisannio.map.UniPoint

interface HomeView {
    fun setTitleToGiurisprudenza()

    fun showProgressbar()
    fun hideProgressbar()

    fun clearList()
    fun addArticles(articles: List<Article>)

    fun openWebsite(website: String)
    fun goToMap(mapMarkers: List<UniPoint>)
}
