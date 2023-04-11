package com.yasinskyi.android.edu2.data.network.retrofit

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class OkHttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) = Timber.d(message)
}