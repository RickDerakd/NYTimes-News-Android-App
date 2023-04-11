package com.yasinskyi.android.edu2.data.exception

import java.io.IOException

class InternetConnectionException(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)