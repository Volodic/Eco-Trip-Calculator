package com.vts.co2checkertest.constants

import androidx.compose.ui.graphics.Color
import com.vts.co2checkertest.ui.theme.Blue
import com.vts.co2checkertest.ui.theme.Green
import com.vts.co2checkertest.ui.theme.Red
import com.vts.co2checkertest.ui.theme.Yellow

enum class TransportColor(val transportType: TransportType, val color: Color) {
    CAR(TransportType.CAR, Blue),
    BUS(TransportType.BUS, Green),
    TRAIN(TransportType.TRAIN, Yellow),
    BIKE(TransportType.BIKE, Red)
}