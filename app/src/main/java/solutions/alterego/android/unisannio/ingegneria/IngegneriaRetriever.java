package solutions.alterego.android.unisannio.ingegneria;

import java.util.List;

import rx.Observable;
import solutions.alterego.android.unisannio.core.Article;

public interface IngegneriaRetriever {

    Observable<List<Article>> get();
}
