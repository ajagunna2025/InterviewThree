package com.example.interviewthree.model.remote.dto

import com.google.gson.annotations.SerializedName

data class StatsResponse(
    val code: String,
    @SerializedName("data")
    val statsDataDTO: StatsDataDTO
)