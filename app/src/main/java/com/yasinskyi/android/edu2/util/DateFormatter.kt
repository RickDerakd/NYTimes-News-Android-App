package com.yasinskyi.android.edu2.util

import android.annotation.SuppressLint
import java.util.Date
import java.text.SimpleDateFormat

object DateFormatter {

    private const val simpleDotPattern: String = "dd.MM.yyyy"

    @SuppressLint("SimpleDateFormat")
    private val simpleDotFormatter = SimpleDateFormat(simpleDotPattern)

    fun formatLongToTime(time: Long): String {
        return simpleDotFormatter.format(Date(time))
    }
}