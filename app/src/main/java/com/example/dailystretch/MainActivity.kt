package com.example.dailystretch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dailystretch.presentation.ui.DailyStretchTheme
import com.example.dailystretch.presentation.view.DailyStretchApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyStretchTheme {
                DailyStretchApp()
            }
        }
    }
}
