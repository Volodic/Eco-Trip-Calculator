package com.vts.co2checkertest.model

import androidx.compose.ui.graphics.Color

data class ChartData(
    val value: Float,
    val color: Color,
    val transportType: String
)
