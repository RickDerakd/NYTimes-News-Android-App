package com.yasinskyi.android.edu2.util.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

private const val timesApiPattern: String = "yyyy-MM-dd'T'HH:mm:ssZ"

@SuppressLint("SimpleDateFormat")
fun String.toMills(): Long {
    return SimpleDateFormat(timesApiPattern).parse(this).time
}