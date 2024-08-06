package com.example.pokemondetails.android.screens.homescreen

import PokemonListScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import networking.models.ListPokemonItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    onItemClick: (ListPokemonItem) -> Unit,
) {
    val state by viewModel.loadingState.observeAsState()

    Box(
        modifier.background(MaterialTheme.colorScheme.background)
    ) {
        when (val currentState = state) {
            is HomeScreenViewModel.LoadingState.Content -> PokemonListScreen(
                pokemonList = currentState.pokemon,
                loadMore = { viewModel.loadMorePokemon() },
                onItemClick = onItemClick,
                isLoadingMore = currentState.isLoadingMore
            )

            is HomeScreenViewModel.LoadingState.Error -> ErrorScreen(
                onRetry = { viewModel.loadInitialPokemon() },
            )

            HomeScreenViewModel.LoadingState.InitialLoading -> LoadingScreen()
            null -> LoadingScreen()
        }
    }
}

@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(viewModel = HomeScreenViewModel()) {}
}