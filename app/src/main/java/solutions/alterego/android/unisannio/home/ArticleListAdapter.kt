package solutions.alterego.android.unisannio.home

import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.article_card.view.article_card_date
import kotlinx.android.synthetic.main.article_card.view.article_card_description
import kotlinx.android.synthetic.main.article_card.view.article_card_title
import solutions.alterego.android.unisannio.R.layout
import solutions.alterego.android.unisannio.core.Article
import solutions.alterego.android.unisannio.home.ArticleListAdapter.ArticleViewHolder

class ArticleListAdapter : Adapter<ArticleViewHolder>() {

    private var articles: MutableList<Article> = mutableListOf()

    fun addArticles(list: List<Article>) {
        articles.clear()
        articles.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        articles.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout.article_card, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) = holder.bind(articles[position])

    class ArticleViewHolder(view: View) : ViewHolder(view) {
        fun bind(article: Article) {
            itemView.article_card_date.text = article.date.takeIf { it.isNotEmpty() } ?: "Recentemente"
            itemView.article_card_title.text = article.title
            itemView.article_card_description.text = Html.fromHtml(article.body).trim()
        }
    }
}
