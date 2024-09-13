package com.vts.co2checkertest.data.model

import androidx.compose.ui.graphics.Color

data class ChartData(
    val value: Double?,
    val color: Color,
    val transportType: String
)
