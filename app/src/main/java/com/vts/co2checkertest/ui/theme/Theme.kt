package com.vts.co2checkertest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MainTheme = lightColorScheme(
    background = LightGray
)

@Composable
fun CO2CheckerTestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MainTheme,
        typography = Typography,
        content = content
    )
}