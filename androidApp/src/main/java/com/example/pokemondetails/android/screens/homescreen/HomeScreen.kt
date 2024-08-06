package com.example.pokemondetails.android.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = HomeScreenViewModel()
    Box(modifier
        .background(MaterialTheme.colorScheme.background)) {
        when (val state = viewModel.loadingState.value) {
            is HomeScreenViewModel.LoadingState.Done -> TODO()
            is HomeScreenViewModel.LoadingState.Error -> ErrorScreen(onRetry = { viewModel.loadPokemon() })
            HomeScreenViewModel.LoadingState.Loading -> LoadingScreen()
            null -> LoadingScreen()
        }
    }
}

@Preview(name = "HomeScreen", showBackground = true
)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}