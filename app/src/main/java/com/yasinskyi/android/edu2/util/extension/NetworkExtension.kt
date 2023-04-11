package com.yasinskyi.android.edu2.util.extension

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

@Suppress("DEPRECATION")
fun ConnectivityManager.hasInternetConnection(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = activeNetwork ?: return false
        val activeNetwork = getNetworkCapabilities(network) ?: return false

        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
    } else {
        val networkInfo = activeNetworkInfo ?: return false

        return networkInfo.type == ConnectivityManager.TYPE_WIFI ||
                networkInfo.type == ConnectivityManager.TYPE_MOBILE ||
                networkInfo.type == ConnectivityManager.TYPE_ETHERNET ||
                networkInfo.type == ConnectivityManager.TYPE_BLUETOOTH
    }
}