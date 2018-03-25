package solutions.alterego.android.unisannio.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.article_recyclerview
import kotlinx.android.synthetic.main.activity_home.bottom_navigation
import kotlinx.android.synthetic.main.activity_home.home_progressbar
import solutions.alterego.android.unisannio.App
import solutions.alterego.android.unisannio.R.drawable
import solutions.alterego.android.unisannio.R.id
import solutions.alterego.android.unisannio.R.layout
import solutions.alterego.android.unisannio.core.Article
import solutions.alterego.android.unisannio.utils.gone
import solutions.alterego.android.unisannio.utils.visible
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    private val adapter = ArticleListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_home)
        setSupportActionBar(findViewById(id.home_toolbar))
        supportActionBar?.title = "Ateneo"
        presenter.attach(this)

        article_recyclerview
            .apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = this@HomeActivity.adapter
                val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                dividerItemDecoration.setDrawable(getDrawable(drawable.list_divider))
                addItemDecoration(dividerItemDecoration)
            }

        bottom_navigation
            .setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    id.navigation_ateneo -> {
                        supportActionBar?.title = "Ateneo"

                        adapter.clear()
                    }
                    id.navigation_ingegneria -> {
                        supportActionBar?.title = "Ingegneria"

                        adapter.clear()
                    }
                    id.navigation_scienze -> {
                        supportActionBar?.title = "Scienze"

                        adapter.clear()
                    }
                    id.navigation_giurisprudenza -> presenter.onGiurisprudenzaClicked()
                    id.navigation_economia -> {
                        supportActionBar?.title = "S.E.A"

                        adapter.clear()
                    }
                }
                true
            }
    }

    override fun clearList() = adapter.clear()

    override fun setTitleToGiurisprudenza() {
        supportActionBar?.title = "Giurisprudenza"
    }

    override fun showProgressbar() = home_progressbar.visible()

    override fun addArticles(articles: List<Article>) = adapter.addArticles(articles)

    override fun hideProgressbar() = home_progressbar.gone()
}
