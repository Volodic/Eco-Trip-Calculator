package com.vts.co2checkertest.ui.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vts.co2checkertest.ui.component.CustomLazyColumn
import com.vts.co2checkertest.ui.component.ScaffoldWithDrawer

@Composable
fun HistoryScreen(navController: NavController, title: String, historyViewModel: HistoryViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        historyViewModel.loadData()
    }
    
    val savedTrips by remember { historyViewModel.userTrips }
    
    ScaffoldWithDrawer(title = title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomLazyColumn(trips = savedTrips)
        }
    }
}