package com.yasinskyi.android.edu2.ui.fragment.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.yasinskyi.android.edu2.databinding.FragmentArticleBinding
import com.yasinskyi.android.edu2.mvvm.viewmodel.articledetails.ArticleDetailsViewModel
import com.yasinskyi.android.edu2.ui.BaseFragment
import com.yasinskyi.android.edu2.ui.util.ArticleWebViewClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : BaseFragment<FragmentArticleBinding, ArticleDetailsViewModel>() {

    override val viewModel: ArticleDetailsViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentArticleBinding {
        return FragmentArticleBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = getUrl()

        binding.apply {
                articleWebView.webViewClient =
                ArticleWebViewClient(
                    { showLoading(true) },
                    { showLoading(false) },
                )

            if (url != null) {
                articleWebView.loadUrl(url)
            }
        }
    }

    private fun getUrl() = arguments?.getString(URL_ARG)

    companion object {

        private const val URL_ARG = "URL_ARG"

        fun newInstance(url: String) =
            ArticleDetailsFragment().apply { arguments = bundleOf(URL_ARG to url) }
    }
}