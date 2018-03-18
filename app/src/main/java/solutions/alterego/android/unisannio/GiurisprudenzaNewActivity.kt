package solutions.alterego.android.unisannio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import solutions.alterego.android.unisannio.core.mapOnSuccess
import solutions.alterego.android.unisannio.core.onError
import solutions.alterego.android.unisannio.core.onSuccess
import timber.log.Timber

class GiurisprudenzaNewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        App.component(this).inject(this)

        launch(UI) {
            val section = Giurisprudenza.sections[0]
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
    }
}
