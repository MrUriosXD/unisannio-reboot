package solutions.alterego.android.unisannio.home

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import solutions.alterego.android.unisannio.Faculty
import solutions.alterego.android.unisannio.Giurisprudenza
import solutions.alterego.android.unisannio.core.mapOnSuccess
import solutions.alterego.android.unisannio.core.onError
import solutions.alterego.android.unisannio.core.onSuccess
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomePresenter @Inject constructor() {

    private var view: HomeView? = null

    fun attach(view: HomeView) {
        this.view = view
    }

    private var faculty: Faculty? = null

    fun onGiurisprudenzaClicked() {
        faculty = Giurisprudenza

        view?.clearList()
        view?.setTitleToGiurisprudenza()

        launch(UI) {
            view?.showProgressbar()
            val section = Giurisprudenza.sections.first()
            val elements = async { section.retriever.fetchItems() }.await()

            elements
                .mapOnSuccess { section.parser.parse(it) }
                .mapOnSuccess { resultOfArticleList ->
                    resultOfArticleList
                        .onSuccess {
                            view?.addArticles(it)
                            view?.hideProgressbar()
                        }
                        .onError {
                            view?.hideProgressbar()
                            Timber.e(it)
                        }
                }
                .onError {
                    view?.hideProgressbar()
                    Timber.e(it)
                }
        }
    }

    fun goToWebsite() {
        if (faculty == null) return
        else {
            view?.openWebsite(faculty?.website!!)
        }
    }

    fun goToMap() {
        Timber.d("Go to Map")
    }

    fun detach() {
        this.view = null
    }
}
