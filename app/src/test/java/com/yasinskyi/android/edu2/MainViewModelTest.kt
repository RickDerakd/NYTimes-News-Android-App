package com.yasinskyi.android.edu2

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.yasinskyi.android.edu2.mvvm.viewmodel.main.MainViewModel
import com.yasinskyi.android.edu2.ui.navigation.Screens
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import io.mockk.confirmVerified
import org.junit.Test

class MainViewModelTest : ViewModelTest<MainViewModel>() {

    override fun createViewModel() = MainViewModel(mockRouter)

    @Test
    fun when_navigateToNewsCalled_expect_newsScreenOpened() {
        val fragmentScreen = mockk<FragmentScreen>()

        mockkObject(Screens)
        every { Screens.NewsScreen() } returns fragmentScreen

        testViewModel.navigateToNews()

        verify(exactly = 1) {
            mockRouter.replaceScreen(fragmentScreen)
        }
        confirmVerified(mockRouter)
    }
}