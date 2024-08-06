package networking.models

data class PokemonDetails(
    val name: String,
    val id: Int,
    val mainImageURL: String,
    val otherFormsImageUrl: List<String>,
    val types: List<Type>
) {
    companion object Mock {
        val mockPokemonDetails = PokemonDetails(
            id = 3,
            name = "venusaur",
            mainImageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
            otherFormsImageUrl = listOf(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/3.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/3.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/3.png"
            ),
            types = listOf(
                Type(1, Species("grass", "https://pokeapi.co/api/v2/type/12/")),
                Type(1, Species("poison", "https://pokeapi.co/api/v2/type/4/"))
            )

        )
    }
}