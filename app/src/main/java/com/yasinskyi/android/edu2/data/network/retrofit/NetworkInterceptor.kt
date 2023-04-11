package com.yasinskyi.android.edu2.data.network.retrofit

import android.content.Context
import com.yasinskyi.android.edu2.data.exception.InternetConnectionException
import com.yasinskyi.android.edu2.util.extension.connectivityManager
import com.yasinskyi.android.edu2.util.extension.hasInternetConnection
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class NetworkInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isConnected()) {
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        } else {
            throw InternetConnectionException()
        }
    }

    private fun isConnected(): Boolean {
        return try {
            context.connectivityManager.hasInternetConnection()
        } catch (e: Exception) {
            Timber.e(e.message)
            false
        }
    }
}