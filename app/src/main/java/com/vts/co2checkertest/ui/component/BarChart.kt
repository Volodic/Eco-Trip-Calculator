package com.vts.co2checkertest.ui.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.vts.co2checkertest.model.ChartData
import com.vts.co2checkertest.ui.theme.Blue
import com.vts.co2checkertest.ui.theme.Green
import com.vts.co2checkertest.ui.theme.Grey
import com.vts.co2checkertest.ui.theme.Red
import com.vts.co2checkertest.ui.theme.Typography
import com.vts.co2checkertest.ui.theme.White
import com.vts.co2checkertest.ui.theme.Yellow

@Composable
fun BarChart(data: List<Float>, labels: List<String>) {
    val maxDataValue = data.maxOrNull() ?: 1f
    val yAxisStep = maxDataValue / 5

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(16.dp)) {
        val barWidth = size.width / (data.size * 2)
        val scaleFactor = size.height / maxDataValue
        val offsetX = size.width / 8

        for (i in 0..5) {
            val yValue = i * yAxisStep
            val yOffset = size.height - yValue * scaleFactor

            drawLine(
                color = Grey,
                start = Offset(0f, yOffset),
                end = Offset(size.width, yOffset),
                strokeWidth = 1.dp.toPx()
            )

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${yValue.toInt()} g/km",
                    0f,
                    yOffset - 10,
                    Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 32f
                    }
                )
            }
        }

        data.forEachIndexed { index, value ->
            val barHeight = value * scaleFactor
            val topLeftX = index * 1.5f * barWidth + barWidth + offsetX
            val topRightX = topLeftX + barWidth
            val topY = size.height - barHeight
            val bottomY = size.height
            val path = Path().apply {
                moveTo(topLeftX, bottomY)
                lineTo(topLeftX, topY + 8.dp.toPx())
                quadraticBezierTo(
                    topLeftX,
                    topY,
                    topLeftX + 8.dp.toPx(),
                    topY
                )
                lineTo(topRightX - 8.dp.toPx(), topY)
                quadraticBezierTo(
                    topRightX,
                    topY,
                    topRightX,
                    topY + 8.dp.toPx()
                )
                lineTo(topRightX, bottomY)
                close()
            }

            drawPath(
                path = path,
                color = listOf(Blue, Green, Yellow, Red)[index]
            )
        }
    }
}

@Composable
fun LegendItem(chartData: ChartData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(chartData.color, shape = CircleShape)
            )

            Text(
                text = chartData.transportType,
                style = Typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(chartData.color, shape = CircleShape)
            )
        }
    }
}