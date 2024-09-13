package com.vts.co2checkertest.ui.screen.statistics

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vts.co2checkertest.R
import com.vts.co2checkertest.ui.component.BarChart
import com.vts.co2checkertest.ui.component.LegendItem
import com.vts.co2checkertest.ui.component.ScaffoldWithDrawer
import com.vts.co2checkertest.ui.theme.Typography
import com.vts.co2checkertest.ui.theme.White

@Composable
fun StatisticsScreen(navController: NavController, title: String, statisticsViewModel: StatisticsViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        statisticsViewModel.loadData()
    }

    val statisticsData by remember { statisticsViewModel.chartData }

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
                    BarChart(statisticsData)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.legend_title),
                style = Typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            statisticsData.forEach { chartData ->
                LegendItem(chartData = chartData)
            }
        }
    }
}