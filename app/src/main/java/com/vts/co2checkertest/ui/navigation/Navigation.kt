package com.vts.co2checkertest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vts.co2checkertest.R
import com.vts.co2checkertest.ui.screen.HistoryScreen
import com.vts.co2checkertest.ui.screen.NotesScreen
import com.vts.co2checkertest.ui.screen.StatisticsScreen

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            "screen1/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType})
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: stringResource(id = R.string.statistics_screen)
            StatisticsScreen(navController, title = title)
        }
        composable(
            "screen2/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType})
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: stringResource(id = R.string.notes_screen)
            NotesScreen(navController, title = title)
        }
        composable(
            "screen3/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType})
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: stringResource(id = R.string.history_screen)
            HistoryScreen(navController, title = title)
        }
    }
}