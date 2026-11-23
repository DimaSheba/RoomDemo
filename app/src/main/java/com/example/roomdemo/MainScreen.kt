package com.example.roomdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TitleRow()
    }
}
