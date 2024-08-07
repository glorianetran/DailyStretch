package com.example.dailystretch.presentation.view.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun DailyStretchScaffold(
    topBar: @Composable (() -> Unit)? = null,
    startPadding: Dp = 16.dp,
    endPadding: Dp = 16.dp,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { topBar?.invoke() },
        content = { innerPadding ->
            content(
                PaddingValues(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr) + startPadding,
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr) + endPadding,
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
            )
        }
    )
}
