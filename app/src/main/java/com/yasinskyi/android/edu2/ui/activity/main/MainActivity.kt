package com.yasinskyi.android.edu2.ui.activity.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.yasinskyi.android.edu2.ui.BaseActivity
import com.yasinskyi.android.edu2.databinding.ActivityMainBinding
import com.yasinskyi.android.edu2.mvvm.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) viewModel.navigateToNews()
    }

    override fun inflateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun showLoading(isLoading: Boolean) {
        binding.loadingContainerView.isVisible = isLoading
        binding.fragmentContainerView.isVisible = !isLoading
    }
}