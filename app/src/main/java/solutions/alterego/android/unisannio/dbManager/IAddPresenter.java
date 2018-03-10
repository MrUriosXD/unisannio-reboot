package solutions.alterego.android.unisannio.dbManager;


import java.util.List;

import solutions.alterego.android.unisannio.core.Article;

 interface IAddPresenter {

    boolean addArticle(Article Article);
    boolean addArticles(List<Article> articles);
    List<Article> setUpDepartment(List<Article> articles);
}
