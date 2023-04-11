package com.yasinskyi.android.edu2.mvvm.viewmodel.news

import com.github.terrakok.cicerone.Router
import com.yasinskyi.android.edu2.data.NewsRepository
import com.yasinskyi.android.edu2.data.exception.InternetConnectionException
import com.yasinskyi.android.edu2.mvvm.interactor.NewsValidationInteractor
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import com.yasinskyi.android.edu2.ui.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    router: Router,
    private val newsRepository: NewsRepository,
    private val newsValidationInteractor: NewsValidationInteractor,
) : BaseViewModel(router) {

    private val _newsState = MutableStateFlow(listOf<Article>())
    val newsState = _newsState.asStateFlow()

    override fun onError(cause: Throwable) {
        when (cause) {
            is InternetConnectionException -> {
                if (newsState.value.isEmpty()) updateErrorState(cause)
            }
            else -> super.onError(cause)
        }
    }

    fun loadNews() {
        launch {
            if (!loadingState.value && _newsState.value.size < MAX_API_OFFSET) {
                updateLoadingState(true)

                newsRepository
                    .getArticles(PAGE_SIZE, _newsState.value.size)
                    .collect { newsPage ->
                        updateNewsState(newsPage)
                        updateLoadingState(false)
                    }
            }
        }
    }

    private fun updateNewsState(newsPage: List<Article>) {
        _newsState.value =
            (_newsState.value + newsValidationInteractor(newsPage))
                .distinct()
    }

    fun navigateToArticleDetailsScreen(link: String) {
        router.navigateTo(Screens.ArticleDetailsScreen(link))
    }

    companion object {

        const val PAGE_SIZE = 20
        const val MAX_API_OFFSET = 500
    }
}