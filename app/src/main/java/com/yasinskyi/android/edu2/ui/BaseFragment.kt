package com.yasinskyi.android.edu2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.yasinskyi.android.edu2.R
import com.yasinskyi.android.edu2.data.exception.InternetConnectionException
import com.yasinskyi.android.edu2.mvvm.viewmodel.BaseViewModel
import com.yasinskyi.android.edu2.util.extension.observeWhenCreated

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @CallSuper protected open fun initObserver() {
        observeWhenCreated(viewModel.loadingState, ::showLoading)
        observeWhenCreated(viewModel.errorState, ::showError)
    }

    protected fun getBaseActivity(): BaseActivity<*, *>? {
        return (context as? BaseActivity<*, *>)
    }

    protected open fun showError(cause: Throwable) {
        when (cause) {
            is InternetConnectionException -> {
                getBaseActivity()
                    ?.provideMessageInterface()
                    ?.showMessage(
                        R.string.errorMessageTitle,
                        R.string.noInternetConnectionMessage,
                        R.string.ok,
                    )
            }
            else -> {
                getBaseActivity()?.showError(cause)
            }
        }
    }

    protected open fun showLoading(isLoading: Boolean) {
        getBaseActivity()?.showLoading(isLoading)
    }

    protected abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}