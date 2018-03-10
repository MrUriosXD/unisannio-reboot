package solutions.alterego.android.unisannio.giurisprudenza;

import java.util.List;

import solutions.alterego.android.unisannio.core.Article;

interface GiurisprudenzaView {

    void setArticles(List<Article> articles);

    void stopProgress();
}
