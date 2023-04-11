package com.yasinskyi.android.edu2.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow

fun <T> LifecycleOwner.observeWhenCreated(producer: Flow<T>, receiver: (T) -> Unit) {
    lifecycleScope.launchWhenCreated {
        producer.collect {
            receiver(it)
        }
    }
}