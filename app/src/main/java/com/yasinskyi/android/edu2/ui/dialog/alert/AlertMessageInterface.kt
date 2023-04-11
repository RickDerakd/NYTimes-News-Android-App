package com.yasinskyi.android.edu2.ui.dialog.alert

import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yasinskyi.android.edu2.ui.dialog.MessageInterface

class AlertMessageInterface(
    private val fragmentActivity: FragmentActivity
) : MessageInterface {

    override fun showMessage(
        @StringRes messageTitleId: Int,
        @StringRes messageResId: Int,
        @StringRes positiveButtonTextId: Int,
    ) {
        MaterialAlertDialogBuilder(fragmentActivity)
            .setTitle(fragmentActivity.getString(messageTitleId))
            .setMessage(fragmentActivity.getString(messageResId))
            .setPositiveButton(fragmentActivity.getString(positiveButtonTextId)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}