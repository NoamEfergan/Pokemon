package networking.models

data class ListPokemonItem(val name: String, val iconURL: String, val passThroughURL: String) {
    companion object Mock {
        val list = listOf(
            ListPokemonItem(
                "Pikachu",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Bulbasaur",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Charmander",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Squirtle",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Caterpie",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Weedle",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/013.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Pidgey",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/016.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            ListPokemonItem(
                "Rattata",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/019.png",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
        )

    }
}