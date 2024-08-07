package com.example.dailystretch.presentation.view.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyStretchAppBar(
    title: String,
    onNavigationClick: () -> Unit = {},
    navigationIcon: ImageVector? = null,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navigationIcon?.let {
            {
                IconButton(onClick = onNavigationClick) {
                    Icon(imageVector = it, contentDescription = "Back")
                }
            }
        } ?: {},
        actions = actions
    )
}

@Preview
@Composable
fun DailyStretchBarPreview() {
    DailyStretchAppBar(title = "Select Routine")
}
