package com.example.roomdemo

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScreenSetup() {
    val viewModel: MainViewModel = viewModel()

    MainScreen(viewModel = viewModel)
}
