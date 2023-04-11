package com.yasinskyi.android.edu2.data.mapper

interface BaseMapper<T, R> : (T) -> R {

    override operator fun invoke(item: T): R
}