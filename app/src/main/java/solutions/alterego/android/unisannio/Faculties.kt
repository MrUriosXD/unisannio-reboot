package solutions.alterego.android.unisannio

import solutions.alterego.android.unisannio.core.ArticleParser
import solutions.alterego.android.unisannio.core.Parser
import solutions.alterego.android.unisannio.core.net.ElementsRetriever
import solutions.alterego.android.unisannio.core.net.Retriever
import solutions.alterego.android.unisannio.map.UniPoint
import solutions.alterego.android.unisannio.map.UnisannioGeoData

class Faculty(
    val name: String /* Ingegneria */,
    val website: String /* https://www.ding.unisannio.it/ */,
    val mapMarkers: List<UniPoint>,
    val sections: List<Section>,
    val detailParser: Parser? = null
)

data class Section(
    val name: String /* Avvisi Studenti */,
    val url: String /* https://www.ding.unisannio.it/en/avvisi-com/avvisi-didattica# */,
    val parser: Parser,
    val retriever: Retriever
)

val Ingegneria = Faculty(
    name = "Ingegneria",
    website = "http://www.ding.unisannio.it/",
    mapMarkers = UnisannioGeoData.INGEGNERIA(),
    sections = listOf(
        Section(
            name = "Avvisi Studenti",
            url = "http://www.ding.unisannio.it/html/rss/rss.php",
            parser = ArticleParser(),
            retriever = ElementsRetriever("http://www.ding.unisannio.it/html/rss/rss.php", "item")
        )
    )
)

val Giurisprudenza = Faculty(
    "Giurisprudenza",
    "http://www.giurisprudenza.unisannio.it/",
    UnisannioGeoData.GIURISPRUDENZA(),
    listOf(
        Section(
            name = "Avvisi",
            url = "http://www.giurisprudenza.unisannio.it/index.php?option=com_rss&catid=2",
            parser = ArticleParser(),
            retriever = ElementsRetriever("http://www.giurisprudenza.unisannio.it/index.php?option=com_rss&catid=2", "item")
        )
    )
)

val Scienze = Faculty(
    "Scienze e Tecnologie",
    "http://www.dstunisannio.it",
    UnisannioGeoData.SCIENZE(),
    listOf(
        Section(
            "Avvisi",
            "http://www.dstunisannio.it/index.php/didattica?format=feed&type=rss",
            ArticleParser(),
            ElementsRetriever("http://www.dstunisannio.it/index.php/didattica?format=feed&type=rss", "item")
        )
    )
)

val Sea = Faculty(
    "SEA",
    "http://www.didatticademm.it/",
    UnisannioGeoData.SEA(),
    listOf(
        Section(
            "Avvisi",
            "http://www.didatticademm.it/index.php?format=feed&type=rss",
            ArticleParser(),
            ElementsRetriever("http://www.didatticademm.it/index.php?format=feed&type=rss", "item")
        )
    )
)
