package com.example.dailystretch.presentation.view.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddStretchScreen(navigation: NavHostController) {
    val navController = rememberNavController()
    AddStretchContent()
}

@Composable
fun AddStretchContent() {
    Text("ADD SCREEN")
}

@Preview(showBackground = true)
@Composable
fun AddStretchScreenPreview() {
    AddStretchContent()
}
