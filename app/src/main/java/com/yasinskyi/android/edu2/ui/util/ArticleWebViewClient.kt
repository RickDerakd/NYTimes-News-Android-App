package com.yasinskyi.android.edu2.ui.util

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class ArticleWebViewClient(
    private val onPageStarted: () -> Unit,
    private val onPageCommitVisible: () -> Unit,
) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        onPageStarted()
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        super.onPageCommitVisible(view, url)
        onPageCommitVisible()
    }
}