package com.example.interviewthree.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.interviewthree.service.StatsService
import com.example.interviewthree.ui.theme.InterviewThreeTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 1 button
 * on button click fetch data from api
 * display lastValue property from response
 */
@AndroidEntryPoint
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    private val serviceIntent by lazy {
        Intent(this, StatsService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            InterviewThreeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KuApp(
                        startService = { startService(serviceIntent) },
                        stopService = { stopService(serviceIntent) },
                        widthSizeClass = widthSizeClass,
                    )
                }
            }
        }
    }
}