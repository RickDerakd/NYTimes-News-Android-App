package com.yasinskyi.android.edu2.ui.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.yasinskyi.android.edu2.databinding.FragmentNewsBinding
import com.yasinskyi.android.edu2.mvvm.entity.Article
import com.yasinskyi.android.edu2.mvvm.viewmodel.news.NewsViewModel
import com.yasinskyi.android.edu2.ui.BaseFragment
import com.yasinskyi.android.edu2.ui.adapter.news.NewsAdapter
import com.yasinskyi.android.edu2.util.extension.isScrolledToEnd
import com.yasinskyi.android.edu2.util.extension.observeWhenCreated
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override val viewModel: NewsViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentNewsBinding {
        return FragmentNewsBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = NewsAdapter(viewModel::navigateToArticleDetailsScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            articleListView.adapter = newsAdapter
            retryButtonView.setOnClickListener {
                viewModel.loadNews()
            }
        }
        addScrolledPageListener()

        if (savedInstanceState == null) viewModel.loadNews()
    }

    override fun initObserver() {
        super.initObserver()
        observeWhenCreated(viewModel.newsState, ::showNews)
    }

    private fun addScrolledPageListener() {
        binding.articleListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (recyclerView.isScrolledToEnd(PAGE_SIZE)) {
                    viewModel.loadNews()
                }
            }
        })
    }

    private fun showNews(articleList: List<Article>) {
        binding.emptyListView.isVisible = articleList.isEmpty()
        binding.articleListView.isVisible = articleList.isNotEmpty()
        newsAdapter.submitList(articleList)
    }

    override fun showLoading(isLoading: Boolean) {
        super.showLoading(isLoading && newsAdapter.currentList.isEmpty())
        if (isLoading) newsAdapter.showLoading()
    }

    companion object {

        const val PAGE_SIZE = 20

        fun newInstance() = NewsFragment()
    }
}