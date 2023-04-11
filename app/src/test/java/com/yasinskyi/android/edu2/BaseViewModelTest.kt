package com.yasinskyi.android.edu2

import com.github.terrakok.cicerone.Router
import com.yasinskyi.android.edu2.ViewModelTest
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import io.mockk.confirmVerified
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest : ViewModelTest<BaseViewModel>() {

    override fun createViewModel() = TestViewModel(mockRouter)

    @Test
    fun when_emitException_errorStateUpdated() = runTest {
        val expectedError = IOException()
        val buffer = MutableSharedFlow<Throwable>(replay = 1)

        scope.launch { testViewModel.errorState.collect(buffer::emit) }

        testViewModel.launch { throw expectedError }

        assertEquals(expectedError, buffer.first())
    }

    @Test
    fun when_exitCalled_expect_routerExitCalled() {
        testViewModel.exit()

        verify(exactly = 1) {
            mockRouter.exit()
        }
        confirmVerified(mockRouter)
    }

    class TestViewModel(mockRouter: Router) : BaseViewModel(mockRouter)
}