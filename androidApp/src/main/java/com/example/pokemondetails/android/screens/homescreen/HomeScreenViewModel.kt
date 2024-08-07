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
        data object InitialLoading : LoadingState()
        data class Content(val pokemon: List<ListPokemonItem>, val isLoadingMore: Boolean = false) :
            LoadingState()

        data class Error(val errorMessage: String) : LoadingState()
    }

    private val _loadingState = MutableLiveData<LoadingState>(LoadingState.InitialLoading)
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val fetcher: PokemonFetcher = PokemonFetcher()
    private var pokemonList: List<ListPokemonItem> = emptyList()

    init {
        loadInitialPokemon()
    }

    fun loadInitialPokemon() {
        viewModelScope.launch {
            try {
                pokemonList = fetcher.fetchPokemonList()
                _loadingState.value = LoadingState.Content(pokemonList)
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loadMorePokemon() {
        viewModelScope.launch {
            val currentState = _loadingState.value
            if (currentState is LoadingState.Content && !currentState.isLoadingMore) {
                _loadingState.value =
                    LoadingState.Content(currentState.pokemon, isLoadingMore = true)
                try {
                    val newPokemon = fetcher.fetchPokemonList()
                    pokemonList = pokemonList + newPokemon
                    _loadingState.value = LoadingState.Content(pokemonList)
                } catch (e: Exception) {
                    _loadingState.value =
                        LoadingState.Content(currentState.pokemon, isLoadingMore = false)
                }
            }
        }
    }
}
