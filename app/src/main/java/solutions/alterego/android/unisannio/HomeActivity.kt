package solutions.alterego.android.unisannio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.bottom_navigation
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import solutions.alterego.android.unisannio.core.mapOnSuccess
import solutions.alterego.android.unisannio.core.onError
import solutions.alterego.android.unisannio.core.onSuccess
import timber.log.Timber


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.title = "Ateneo"

        launch(UI) {
            val section = Giurisprudenza.sections.first()
            val elements = async { section.retriever.fetchItems() }.await()

            elements
                .mapOnSuccess { section.parser.parse(it) }
                .mapOnSuccess { resultOfArticleList ->
                    resultOfArticleList
                        .onSuccess {
                            it.forEach { Timber.d(it.toString()) }
                        }
                        .onError { Timber.e(it) }
                }
                .onError { Timber.e(it) }
        }


        bottom_navigation
            .setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_ateneo -> {
                        supportActionBar?.title = "Ateneo"
                    }
                    R.id.navigation_ingegneria -> {
                        supportActionBar?.title = "Ingegneria"
                    }
                    R.id.navigation_scienze -> {
                        supportActionBar?.title = "Scienze"
                    }
                    R.id.navigation_giurisprudenza -> {
                        supportActionBar?.title = "Giurisprudenza"
                    }
                    R.id.navigation_economia -> {
                        supportActionBar?.title = "S.E.A"
                    }
                }
                true
            }
    }
}
