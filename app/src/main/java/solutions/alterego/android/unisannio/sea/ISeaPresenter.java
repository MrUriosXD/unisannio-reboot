package solutions.alterego.android.unisannio.sea;

import java.util.ArrayList;

import rx.Observable;
import solutions.alterego.android.unisannio.core.Article;

public interface ISeaPresenter {
    Observable<ArrayList<Article>> getArticles();
}
