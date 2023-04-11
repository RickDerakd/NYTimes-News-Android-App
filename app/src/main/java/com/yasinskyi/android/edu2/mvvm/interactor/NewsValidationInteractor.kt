package com.yasinskyi.android.edu2.mvvm.interactor

import com.yasinskyi.android.edu2.mvvm.entity.Article

class NewsValidationInteractor {

    operator fun invoke(articleList: List<Article>): List<Article> {
        return articleList
            .filter { isValid(it) }
    }

    private fun isValid(article: Article): Boolean {
        return article.publishedDate > 0 &&
                !article.title.isNullOrEmpty()
    }
}
