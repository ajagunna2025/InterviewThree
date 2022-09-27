package com.example.interviewthree.model.remote

import com.example.interviewthree.model.remote.dto.StatsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KuCoinService {

    @GET("/api/v1/market/stats")
    suspend fun getStats(@Query("symbol") symbol: String): StatsResponse
}