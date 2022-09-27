package com.example.interviewthree.model.remote.dto

data class StatsDataDTO(
    val averagePrice: String,
    val buy: String,
    val changePrice: String,
    val changeRate: String,
    val high: String,
    val last: String,
    val low: String,
    val makerCoefficient: String,
    val makerFeeRate: String,
    val sell: String,
    val symbol: String,
    val takerCoefficient: String,
    val takerFeeRate: String,
    val time: Long,
    val vol: String,
    val volValue: String
)