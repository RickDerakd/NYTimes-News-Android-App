package com.yasinskyi.android.edu2.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.yasinskyi.android.edu2.ui.fragment.articledetails.ArticleDetailsFragment
import com.yasinskyi.android.edu2.ui.fragment.news.NewsFragment

object Screens {

    fun NewsScreen() = FragmentScreen {
        NewsFragment.newInstance()
    }

    fun ArticleDetailsScreen(url: String) = FragmentScreen {
        ArticleDetailsFragment.newInstance(url)
    }
}