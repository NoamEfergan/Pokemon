package networking.models

data class ListPokemonItem(val name: String, val iconURL: String) {
    companion object Mock {
        val list = listOf(
            ListPokemonItem(
                "Pikachu",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png"
            ),
            ListPokemonItem(
                "Bulbasaur",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"
            ),
            ListPokemonItem(
                "Charmander",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png"
            ),
            ListPokemonItem(
                "Squirtle",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png"
            ),
            ListPokemonItem(
                "Caterpie",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png"
            ),
            ListPokemonItem(
                "Weedle",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/013.png"
            ),
            ListPokemonItem(
                "Pidgey",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/016.png"
            ),
            ListPokemonItem(
                "Rattata",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/019.png"
            ),
        )

    }
}