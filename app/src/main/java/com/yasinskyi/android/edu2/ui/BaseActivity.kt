package com.yasinskyi.android.edu2.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.yasinskyi.android.edu2.R
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import com.yasinskyi.android.edu2.ui.dialog.MessageInterface
import com.yasinskyi.android.edu2.util.extension.observeWhenCreated
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    protected lateinit var navigatorHolder: NavigatorHolder

    @Inject
    protected lateinit var navigator: AppNavigator

    @Inject
    protected lateinit var alertMessageInterface: MessageInterface

    protected lateinit var binding: VB
        private set

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        setContentView(binding.root)
        initObserver()
    }

    override fun onBackPressed() {
        viewModel.exit()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    @CallSuper protected open fun initObserver() {
        observeWhenCreated(viewModel.loadingState, ::showLoading)
        observeWhenCreated(viewModel.errorState, ::showError)
    }

    fun provideMessageInterface(): MessageInterface {
        return alertMessageInterface
    }

    fun showError(cause: Throwable) {
        alertMessageInterface.showMessage(R.string.errorMessageTitle, R.string.unknownErrorMessage, R.string.ok)
    }

    abstract fun inflateBinding(inflater: LayoutInflater): VB

    abstract fun showLoading(isLoading: Boolean)
}