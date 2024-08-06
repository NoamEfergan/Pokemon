package com.example.pokemondetails.android.screens.pokemon_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import networking.PokemonFetcher
import networking.models.PokemonDetails

class PokemonDetailViewModel(private val id: String) : ViewModel() {

    sealed class LoadingState {
        data object Loading : LoadingState()
        data class Success(val pokemon: PokemonDetails) : LoadingState()
        data class Error(val message: String) : LoadingState()
    }

    private val fetcher: PokemonFetcher = PokemonFetcher()
    private val _pokemon: PokemonDetails? = null
    val pokemon: PokemonDetails?
        get() = _pokemon

    private val _loadingState = MutableLiveData<PokemonDetailViewModel.LoadingState>(
        PokemonDetailViewModel.LoadingState.Loading
    )
    val loadingState: LiveData<PokemonDetailViewModel.LoadingState>
        get() = _loadingState

    init {
        fetchPokemon()
    }

    fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val pokemon = fetcher.fetchPokemonDetails(id)
                _loadingState.value = PokemonDetailViewModel.LoadingState.Success(pokemon)
            } catch (e: Exception) {
                _loadingState.value = PokemonDetailViewModel.LoadingState.Error(e.localizedMessage ?: "Unknown error")
            }
        }

    }

    fun fetchMock() {
        _loadingState.value = PokemonDetailViewModel.LoadingState.Success(PokemonDetails.mockPokemonDetails)
    }

}