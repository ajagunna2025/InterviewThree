package com.example.interviewthree.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatsData(
    val last: String = "",
    val buy: String = "",
    val sell: String = "",
    val symbol: String = "",
    val averagePrice: String = "",
    val changePrice: String = "",
    val changeRate: String = "",
    val low: String = "",
) : Parcelable