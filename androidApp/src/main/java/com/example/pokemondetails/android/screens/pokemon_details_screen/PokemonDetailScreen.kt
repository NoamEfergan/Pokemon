package com.example.pokemondetails.android.screens.pokemon_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemondetails.android.common.ErrorScreen
import com.example.pokemondetails.android.common.LoadingScreen

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel
) {
    val loadingState by viewModel.loadingState.observeAsState()
    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (loadingState) {
            is PokemonDetailViewModel.LoadingState.Loading -> {
                    LoadingScreen()
            }

            is PokemonDetailViewModel.LoadingState.Success -> {
                val pokemon = (loadingState as PokemonDetailViewModel.LoadingState.Success).pokemon
                Text(text = "Name: ${pokemon.name}")
            }

            is PokemonDetailViewModel.LoadingState.Error -> {
                ErrorScreen() {
                    viewModel.fetchPokemon()
                }
            }

            null -> {
                Text(text = "No data")
            }
        }
    }
}

@Preview(name = "PokemonDetailScreen", showBackground = true)
@Composable
private fun PreviewPokemonDetailScreen() {
    val viewModel = PokemonDetailViewModel("0")
    viewModel.fetchMock()
    PokemonDetailScreen(viewModel = viewModel)
}