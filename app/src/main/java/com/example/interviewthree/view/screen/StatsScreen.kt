package com.example.interviewthree.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.interviewthree.service.StatsService
import com.example.interviewthree.util.KuRoute

@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
    start: () -> Unit,
    stop: () -> Unit,
    controller: NavController,
    isLargeScreen: Boolean = false,
) {
    val state by StatsService.serviceStateFlow.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .clickable {
                    stop()
                    controller.currentBackStackEntry?.savedStateHandle?.set("serviceState", state)
                    if (isLargeScreen.not()) controller.navigate(KuRoute.Detail)
                }
        ) {
            with(state) {
                Text(color = textColor, text = "Last: ${statsData.last}")
                Text(color = textColor, text = "Buy: ${statsData.buy}")
                Text(color = textColor, text = "Sell: ${statsData.sell}")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            val btnModifier = Modifier.weight(1f)
            Button(modifier = btnModifier, onClick = stop, enabled = state.isFetching) {
                Text(text = "Stop")
            }
            Button(modifier = btnModifier, onClick = start, enabled = !state.isFetching) {
                Text(text = "Start")
            }
        }
    }
}