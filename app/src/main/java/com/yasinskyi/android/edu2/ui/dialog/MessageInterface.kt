package com.yasinskyi.android.edu2.ui.dialog

import androidx.annotation.StringRes

interface MessageInterface {

    fun showMessage(
        @StringRes messageTitleId: Int,
        @StringRes messageResId: Int,
        @StringRes positiveButtonTextId: Int
    )
}