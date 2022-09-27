package com.example.interviewthree.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.interviewthree.util.KuRoute
import com.example.interviewthree.util.StatsServiceState
import com.example.interviewthree.view.screen.DetailScreen
import com.example.interviewthree.view.screen.StatsScreen
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun KuApp(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    startService: () -> Unit,
    stopService: () -> Unit,
) {

    val navController = rememberNavController()
    val isExpandedScreen =
        widthSizeClass == WindowWidthSizeClass.Medium || widthSizeClass == WindowWidthSizeClass.Expanded

    if (isExpandedScreen) {
        NavHost(
            navController = navController,
            startDestination = KuRoute.Stats,
            modifier = modifier
        ) {
            composable(route = KuRoute.Stats) {
                Row(modifier) {
                    Box(modifier = Modifier.weight(1f)) {
                        StatsScreen(
                            start = startService,
                            stop = stopService,
                            controller = navController,
                            isLargeScreen = true
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        val serviceStateFlow =
                            navController.currentBackStackEntry?.savedStateHandle?.getStateFlow(
                                "serviceState", StatsServiceState()
                            )
                        val state by (serviceStateFlow
                            ?: MutableStateFlow(StatsServiceState())).collectAsState()

                        DetailScreen(state = state)
                    }
                }
            }
        }

    } else {
        NavHost(
            navController = navController,
            startDestination = KuRoute.Stats,
            modifier = modifier
        ) {
            composable(route = KuRoute.Stats) {
                StatsScreen(
                    start = startService,
                    stop = stopService,
                    controller = navController
                )
            }
            composable(route = KuRoute.Detail) {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<StatsServiceState>("serviceState")
                    ?.let { state -> DetailScreen(state = state) }
            }
        }
    }
}