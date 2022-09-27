package com.example.interviewthree.util

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.example.interviewthree.model.local.StatsData
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class StatsServiceState(
    val statsData: StatsData = StatsData(),
    val errorMsg: String = "",
    val isFetching: Boolean = false,
    val textColor: @RawValue Color = Color.Black
) : Parcelable