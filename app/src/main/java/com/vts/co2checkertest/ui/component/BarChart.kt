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
import com.vts.co2checkertest.data.model.ChartData
import com.vts.co2checkertest.ui.theme.Blue
import com.vts.co2checkertest.ui.theme.Green
import com.vts.co2checkertest.ui.theme.Gray
import com.vts.co2checkertest.ui.theme.Red
import com.vts.co2checkertest.ui.theme.Typography
import com.vts.co2checkertest.ui.theme.White
import com.vts.co2checkertest.ui.theme.Yellow

@Composable
fun BarChart(chartData: List<ChartData>) {
    val maxDataValue = chartData.maxOfOrNull { it.value!!.toFloat() } ?: 1f
    val yAxisStep = maxDataValue / 5

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(16.dp)) {
        val barWidth = size.width / (chartData.size * 2)
        val scaleFactor = size.height / maxDataValue
        val offsetX = size.width / 8

        for (i in 0..5) {
            val yValue = i * yAxisStep
            val yOffset = size.height - yValue * scaleFactor

            drawLine(
                color = Gray,
                start = Offset(0f, yOffset),
                end = Offset(size.width, yOffset),
                strokeWidth = 1.dp.toPx()
            )

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${yValue.toInt()} kg/km",
                    0f,
                    yOffset - 10,
                    Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 32f
                    }
                )
            }
        }

        chartData.forEachIndexed { index, data ->
            if (data.value!! > 0) {
                val barHeight = data.value.times(scaleFactor)
                val topLeftX = index * 1.5f * barWidth + barWidth + offsetX
                val topRightX = topLeftX + barWidth
                val topY = size.height - barHeight
                val bottomY = size.height
                val path = Path().apply {
                    moveTo(topLeftX, bottomY)
                    lineTo(topLeftX, (topY + 8.dp.toPx()).toFloat())
                    quadraticBezierTo(
                        topLeftX,
                        topY.toFloat(),
                        topLeftX + 8.dp.toPx(),
                        topY.toFloat()
                    )
                    lineTo(topRightX - 8.dp.toPx(), topY.toFloat())
                    quadraticBezierTo(
                        topRightX,
                        topY.toFloat(),
                        topRightX,
                        (topY + 8.dp.toPx()).toFloat()
                    )
                    lineTo(topRightX, bottomY)
                    close()
                }

                drawPath(
                    path = path,
                    color = listOf(Blue, Green, Yellow, Red)[index]
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        data.value.toString(),
                        topLeftX + barWidth / 2,
                        size.height + 40,
                        Paint().apply {
                            color = android.graphics.Color.BLACK
                            textAlign = Paint.Align.CENTER
                            textSize = 32f
                        }
                    )
                }
            }
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