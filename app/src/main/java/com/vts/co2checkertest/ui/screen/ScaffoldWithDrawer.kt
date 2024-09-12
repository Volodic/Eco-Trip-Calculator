package com.vts.co2checkertest.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vts.co2checkertest.ui.component.CenterAlignedTopAppBarImpl
import com.vts.co2checkertest.ui.theme.AlphaBlack
import com.vts.co2checkertest.ui.theme.LightGray
import kotlinx.coroutines.launch

@Composable
fun ScaffoldWithDrawer(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BoxWithConstraints {
        val drawerWidth = maxWidth * 0.75f

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .width(drawerWidth)
                        .background(LightGray)
                ) {
                    DrawerContent(navController = navController, scope = scope, drawerState = drawerState)
                }
            },
            drawerState = drawerState,
            scrimColor = AlphaBlack
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBarImpl(screenTitle = title) {
                        scope.launch { drawerState.open() }
                    }
                },
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        content()
                    }
                }
            )
        }
    }
}