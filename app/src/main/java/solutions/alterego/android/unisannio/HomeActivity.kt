package solutions.alterego.android.unisannio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.article_recyclerview
import kotlinx.android.synthetic.main.activity_home.bottom_navigation
import kotlinx.android.synthetic.main.activity_home.home_progressbar
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import solutions.alterego.android.unisannio.core.mapOnSuccess
import solutions.alterego.android.unisannio.core.onError
import solutions.alterego.android.unisannio.core.onSuccess
import solutions.alterego.android.unisannio.utils.gone
import solutions.alterego.android.unisannio.utils.visible
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private val adapter = ArticleListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.home_toolbar))
        supportActionBar?.title = "Ateneo"

        article_recyclerview
            .apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = this@HomeActivity.adapter
                val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                dividerItemDecoration.setDrawable(getDrawable(R.drawable.list_divider))
                addItemDecoration(dividerItemDecoration)
            }

        bottom_navigation
            .setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_ateneo -> {
                        supportActionBar?.title = "Ateneo"

                        adapter.clear()
                    }
                    R.id.navigation_ingegneria -> {
                        supportActionBar?.title = "Ingegneria"

                        adapter.clear()
                    }
                    R.id.navigation_scienze -> {
                        supportActionBar?.title = "Scienze"

                        adapter.clear()
                    }
                    R.id.navigation_giurisprudenza -> {
                        supportActionBar?.title = "Giurisprudenza"

                        adapter.clear()

                        launch(UI) {
                            home_progressbar.visible()

                            val section = Giurisprudenza.sections.first()
                            val elements = async { section.retriever.fetchItems() }.await()

                            elements
                                .mapOnSuccess { section.parser.parse(it) }
                                .mapOnSuccess { resultOfArticleList ->
                                    resultOfArticleList
                                        .onSuccess {
                                            adapter.addArticles(it)
                                            home_progressbar.gone()
                                        }
                                        .onError {
                                            home_progressbar.gone()
                                            Timber.e(it)
                                        }
                                }
                                .onError {
                                    home_progressbar.gone()
                                    Timber.e(it)
                                }
                        }
                    }
                    R.id.navigation_economia -> {
                        supportActionBar?.title = "S.E.A"

                        adapter.clear()
                    }
                }
                true
            }
    }
}

