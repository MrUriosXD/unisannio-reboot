package solutions.alterego.android.unisannio.cercapersone;

import java.util.ArrayList;
import rx.Observable;

public interface ICercapersonePresenter {

    Observable<ArrayList<Person>> getPeople();
}
