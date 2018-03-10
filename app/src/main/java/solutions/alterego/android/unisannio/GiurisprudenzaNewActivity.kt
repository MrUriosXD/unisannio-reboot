package solutions.alterego.android.unisannio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import solutions.alterego.android.unisannio.giurisprudenza.GiurisprudenzaNewRetriever
import javax.inject.Inject

class GiurisprudenzaNewActivity : AppCompatActivity() {

    @Inject
    lateinit var retriever: GiurisprudenzaNewRetriever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        App.component(this).inject(this)

        launch(UI) {
            val elements = async { retriever.fetchItems() }
            val articles = retriever.parseItems(elements.await())

            articles.forEach { println(it.toString()) }


        }
    }
}
