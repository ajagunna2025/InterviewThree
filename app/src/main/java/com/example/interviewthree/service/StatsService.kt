package com.example.interviewthree.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.compose.ui.graphics.Color
import com.example.interviewthree.model.KuCoinRepo
import com.example.interviewthree.model.Symbol
import com.example.interviewthree.model.local.StatsData
import com.example.interviewthree.util.StatsServiceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@AndroidEntryPoint
class StatsService : Service() {

    @Inject
    lateinit var repo: KuCoinRepo

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        val errorMsg = throwable.message ?: "UNKNOWN ERROR"
        serviceStateFlow.update { state -> state.copy(errorMsg = errorMsg, isFetching = false) }
        stopSelf()
    }

    private var scope: CoroutineScope = CoroutineScope(
        context = Dispatchers.IO + exceptionHandler + CoroutineName("StatsServiceScope")
    )

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceStateFlow.update { state -> state.copy(isFetching = true) }
        scope.launch {
            while (isActive) {
                delay(DELAY_IN_MILLIS)
                val statsDataDTO = repo.getStats(Symbol.BTC_USDT).statsDataDTO
                val statsData = StatsData(
                    last = statsDataDTO.last,
                    buy = statsDataDTO.buy,
                    sell = statsDataDTO.sell,
                    symbol = statsDataDTO.symbol,
                    averagePrice = statsDataDTO.averagePrice,
                    changePrice = statsDataDTO.changePrice,
                    changeRate = statsDataDTO.changeRate,
                    low = statsDataDTO.low,
                )
                val textColor = if (statsData.changePrice.contains('-')) Color.Red else Color.Green
                serviceStateFlow.update { state ->
                    state.copy(
                        statsData = statsData,
                        textColor = textColor,
                        isFetching = true
                    )
                }
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        if (scope.isActive) scope.cancel()
        serviceStateFlow.update { state -> state.copy(isFetching = false) }
    }

    companion object {
        private const val DELAY_IN_MILLIS = 2000L
        val serviceStateFlow = MutableStateFlow(StatsServiceState())
    }
}