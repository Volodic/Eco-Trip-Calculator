package com.vts.co2checkertest.utils

import kotlin.math.pow

fun Double.toFixedDecimalPlaces(decimalPlaces: Int): Double {
    val factor = 10.0.pow(decimalPlaces).toFloat()
    return kotlin.math.round(this * factor) / factor
}