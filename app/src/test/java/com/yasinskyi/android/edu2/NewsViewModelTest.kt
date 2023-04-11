package com.yasinskyi.android.edu2

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.yasinskyi.android.edu2.ViewModelTest
import com.yasinskyi.android.edu2.data.NewsRepository
import com.yasinskyi.android.edu2.mvvm.interactor.NewsValidationInteractor
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.mvvm.viewmodel.news.NewsViewModel
import com.yasinskyi.android.edu2.ui.navigation.Screens
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.confirmVerified
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest : ViewModelTest<NewsViewModel>() {

    @RelaxedMockK
    private lateinit var mockRepository: NewsRepository

    @RelaxedMockK
    private lateinit var mockValidationInteractor: NewsValidationInteractor

    private val mockLink: String = "https://www.google.com.ua/"

    private val mockArticleList =
        listOf(
            Article(100, mockLink, "Google", "Google", null),
            Article(101, mockLink, "Google1", "Google1", null),
            Article(102, mockLink, "Google2", "Google2", null),
        )

    override fun createViewModel() =
        NewsViewModel(
            mockRouter,
            mockRepository,
            mockValidationInteractor,
        )

    @Test
    fun when_loadNewsCalled_expect_emitNews() = runTest {
        val buffer = MutableStateFlow(listOf<Article>())

        every { mockRepository.getArticles(20, 0) } returns flow { emit(mockArticleList) }
        every { mockValidationInteractor(mockArticleList) } returns mockArticleList

        testViewModel.loadNews()

        scope.launch {
            testViewModel.newsState.collect {
                buffer.emit(it)
            }
        }

        coVerifyOrder {
            mockRepository.getArticles(20, 0)
            mockValidationInteractor(mockArticleList)
        }

        assertEquals(mockArticleList, buffer.value)
    }

    @Test
    fun when_loadNewsCalled_expect_errorHandled() = runTest {
        val unknownError = UnknownError()
        every { mockRepository.getArticles(20, 0) } throws unknownError

        launch {
            testViewModel.loadNews()
        }

        assertEquals(unknownError, testViewModel.errorState.first())
    }

    @Test
    fun when_viewModelNavigateToBrowserCalled_expect_browserScreenOpened() {
        val fragmentScreen = mockk<FragmentScreen>()

        mockkObject(Screens)
        every { Screens.ArticleDetailsScreen(mockLink) } returns fragmentScreen

        testViewModel.navigateToArticleDetailsScreen(mockLink)

        verify(exactly = 1) {
            mockRouter.navigateTo(fragmentScreen)
        }
        confirmVerified(mockRouter)
    }
}