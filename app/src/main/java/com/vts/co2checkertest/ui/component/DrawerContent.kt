package com.vts.co2checkertest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vts.co2checkertest.R
import com.vts.co2checkertest.ui.theme.Gray
import com.vts.co2checkertest.ui.theme.LightGray
import com.vts.co2checkertest.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    ) {
        DrawerMenuItem(text = stringResource(id = R.string.statistics_screen)) {
            scope.launch {
                drawerState.close()
                navController.navigate("screen1/Statistics")
            }
        }
        DrawerMenuItem(text = stringResource(id = R.string.notes_screen)) {
            scope.launch {
                drawerState.close()
                navController.navigate("screen2/Notes")
            }
        }
        DrawerMenuItem(text = stringResource(id = R.string.history_screen)) {
            scope.launch {
                drawerState.close()
                navController.navigate("screen3/History")
            }
        }
    }
}

@Composable
fun DrawerMenuItem(text: String, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Text(
            text = text,
            style = Typography.bodyLarge,
            color = Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 12.dp)
        )
    }
}