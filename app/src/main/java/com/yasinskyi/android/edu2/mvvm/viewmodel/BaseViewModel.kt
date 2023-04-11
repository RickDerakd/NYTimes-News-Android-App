package com.yasinskyi.android.edu2.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    protected val router: Router,
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + exceptionHandler

    protected val exceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }

    protected val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    protected val _errorState = MutableSharedFlow<Throwable>()
    val errorState = _errorState.asSharedFlow()

    protected open fun onError(cause: Throwable) {
        updateLoadingState(false)
        updateErrorState(cause)
    }

    protected fun updateLoadingState(isLoading: Boolean) {
        launch {
            _loadingState.emit(isLoading)
        }
    }

    protected fun updateErrorState(cause: Throwable) {
        launch {
            _errorState.emit(cause)
        }
    }

    fun exit() {
        router.exit()
    }
}