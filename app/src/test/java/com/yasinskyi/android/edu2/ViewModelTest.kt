package com.yasinskyi.android.edu2

import androidx.annotation.CallSuper
import com.github.terrakok.cicerone.Router
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
abstract class ViewModelTest<VM: BaseViewModel> {

    @RelaxedMockK
    lateinit var mockRouter: Router

    protected lateinit var testViewModel: VM

    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    protected val scope: TestScope = TestScope(dispatcher)

    @Before
    @CallSuper
    open fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        testViewModel = createViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cancel()
    }

    abstract fun createViewModel(): VM
}