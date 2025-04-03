package com.saraiva.bipa.ui.screens.util

import android.icu.text.DecimalFormat
import android.text.format.DateFormat
import com.saraiva.bipa.core.Constants
import java.util.Date

fun Long.toBtc(): String {
    val satsToBtc = toBigDecimal().divide(Constants.BTC_SATS.toBigDecimal())

    if (satsToBtc > 1.toBigDecimal())
        return DecimalFormat(Constants.MONEYFORMAT).format(satsToBtc)

    return DecimalFormat(Constants.DECIMALFORMAT).format(satsToBtc)
}

fun Long.toUTC() =
    DateFormat.format(Constants.DATEFORMAT, Date(this * Constants.MILLISECONDS)).toString()