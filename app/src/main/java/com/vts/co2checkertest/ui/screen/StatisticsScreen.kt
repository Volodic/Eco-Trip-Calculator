package com.vts.co2checkertest.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vts.co2checkertest.R
import com.vts.co2checkertest.model.ChartData
import com.vts.co2checkertest.ui.component.BarChart
import com.vts.co2checkertest.ui.component.LegendItem
import com.vts.co2checkertest.ui.theme.Blue
import com.vts.co2checkertest.ui.theme.Green
import com.vts.co2checkertest.ui.theme.Red
import com.vts.co2checkertest.ui.theme.Typography
import com.vts.co2checkertest.ui.theme.White
import com.vts.co2checkertest.ui.theme.Yellow

@Composable
fun StatisticsScreen(navController: NavController, title: String) {
    val data = listOf(120f, 80f, 100f, 40f)
    val labels = listOf("Car", "Bus", "Train", "Bike")

    val chartData = listOf(
        ChartData(value = 100f, color = Blue, transportType = "Car"),
        ChartData(value = 75f, color = Green, transportType = "Bus"),
        ChartData(value = 50f, color = Yellow, transportType = "Train"),
        ChartData(value = 25f, color = Red, transportType = "Bike")
    )

    ScaffoldWithDrawer(title = title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BarChart(data = data, labels = labels)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.legend_title),
                style = Typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            chartData.forEach { chartData ->
                LegendItem(chartData = chartData)
            }
        }
    }
}