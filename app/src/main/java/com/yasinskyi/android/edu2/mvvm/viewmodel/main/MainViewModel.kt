package com.yasinskyi.android.edu2.mvvm.viewmodel.main

import com.github.terrakok.cicerone.Router
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import com.yasinskyi.android.edu2.ui.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    router: Router,
) : BaseViewModel(router) {

    fun navigateToNews() {
        router.replaceScreen(Screens.NewsScreen())
    }
}