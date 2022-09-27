package com.example.interviewthree.model

import com.example.interviewthree.model.remote.KuCoinService
import javax.inject.Inject

class KuCoinRepo @Inject constructor(private val kuCoinService: KuCoinService) {

    suspend fun getStats(symbol: Symbol) = kuCoinService.getStats(symbol.apiValue)
}