package com.vts.co2checkertest.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertStringToDouble(): Double {
    return try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}

fun Long.convertTimestampToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("EEE - dd.MM.yyyy", Locale.ENGLISH)
    return format.format(date)
}