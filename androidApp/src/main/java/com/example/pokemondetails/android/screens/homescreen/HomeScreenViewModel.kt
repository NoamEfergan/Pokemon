package com.example.pokemondetails.android.screens.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import networking.PokemonFetcher
import networking.models.ListPokemonItem

class HomeScreenViewModel : ViewModel() {
    sealed class LoadingState {
        data object Loading : LoadingState()
        data class Error(val errorMessage: String) : LoadingState()
        data class Done(val pokemon: List<ListPokemonItem>) : LoadingState()
    }

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val fetcher: PokemonFetcher = PokemonFetcher()
    private var pokemonList: List<ListPokemonItem> = emptyList()

    fun loadPokemon() {
        viewModelScope.launch {
            if (pokemonList.isEmpty()) {
                _loadingState.value = LoadingState.Loading
            }
            try {
                val pokemon = fetcher.fetchPokemon()
                pokemonList = pokemonList + pokemon
                _loadingState.value = LoadingState.Done(pokemonList)
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

}