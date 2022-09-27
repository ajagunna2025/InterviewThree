package com.example.interviewthree.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.interviewthree.util.StatsServiceState

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: StatsServiceState,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            with(state) {
                Text(color = textColor, text = "Last: ${statsData.last}")
                Text(color = textColor, text = "Buy: ${statsData.buy}")
                Text(color = textColor, text = "Sell: ${statsData.sell}")
                Text(color = textColor, text = "Symbol: ${statsData.symbol}")
                Text(color = textColor, text = "Average Price: ${statsData.averagePrice}")
                Text(color = textColor, text = "Change Price: ${statsData.changePrice}")
                Text(color = textColor, text = "Change Rate: ${statsData.changeRate}")
                Text(color = textColor, text = "Low: ${statsData.low}")

            }
        }
    }
}