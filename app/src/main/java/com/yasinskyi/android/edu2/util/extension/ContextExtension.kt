package com.yasinskyi.android.edu2.util.extension

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater

inline val Context.layoutInflater: LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

inline val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager