package com.yasinskyi.android.edu2.mvvm.viewmodel.articledetails

import com.github.terrakok.cicerone.Router
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    router: Router,
) : BaseViewModel(router)