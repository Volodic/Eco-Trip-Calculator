package com.vts.co2checkertest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vts.co2checkertest.R
import com.vts.co2checkertest.ui.screen.history.HistoryScreen
import com.vts.co2checkertest.ui.screen.history.HistoryViewModel
import com.vts.co2checkertest.ui.screen.notes.NotesScreen
import com.vts.co2checkertest.ui.screen.notes.NotesViewModel
import com.vts.co2checkertest.ui.screen.statistics.StatisticsScreen
import com.vts.co2checkertest.ui.screen.statistics.StatisticsViewModel

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            "screen1/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType})
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: stringResource(id = R.string.statistics_screen)
            val viewModel = hiltViewModel<StatisticsViewModel>()
            StatisticsScreen(navController, title = title, viewModel)
        }
        composable(
            "screen2/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType})
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: stringResource(id = R.string.notes_screen)
            val viewModel = hiltViewModel<NotesViewModel>()
            NotesScreen(navController, title = title, viewModel)
        }
        composable(
            "screen3/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType})
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: stringResource(id = R.string.history_screen)
            val viewModel = hiltViewModel<HistoryViewModel>()
            HistoryScreen(navController, title = title, viewModel)
        }
    }
}