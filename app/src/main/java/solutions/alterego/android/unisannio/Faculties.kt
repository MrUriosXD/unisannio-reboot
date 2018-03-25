package solutions.alterego.android.unisannio

import solutions.alterego.android.unisannio.R.string
import solutions.alterego.android.unisannio.core.ArticleParser
import solutions.alterego.android.unisannio.core.Parser
import solutions.alterego.android.unisannio.core.net.ElementsRetriever
import solutions.alterego.android.unisannio.core.net.Retriever
import solutions.alterego.android.unisannio.map.UniPoint
import solutions.alterego.android.unisannio.map.UnisannioGeoData

class Faculty(
    val hearderImage: Int,
    val name: String /* Ingegneria */,
    val website: String /* https://www.ding.unisannio.it/ */,
    val mapMarkers: List<UniPoint>,
    val sections: List<Section>,
    val detailParser: Parser? = null
)

data class Section(
    val titleResource: Int /* Avvisi Studenti */,
    val url: String /* https://www.ding.unisannio.it/en/avvisi-com/avvisi-didattica# */,
    val parser: Parser,
    val retriever: Retriever
)

val Ateneo = Faculty(
    0,
    "Ateneo",
    "",
    UnisannioGeoData.ATENEO(),
    listOf()
)

val Giurisprudenza = Faculty(
    0,
    "Giurisprudenza",
    "http://www.giurisprudenza.unisannio.it/",
    UnisannioGeoData.GIURISPRUDENZA(),
    listOf(
        Section(
            titleResource = string.title_activity_giurisprudenza,
            url = "http://www.giurisprudenza.unisannio.it/index.php?option=com_rss&catid=2",
            parser = ArticleParser(),
            retriever = ElementsRetriever("http://www.giurisprudenza.unisannio.it/index.php?option=com_rss&catid=2", "item")
        )
    )
)
