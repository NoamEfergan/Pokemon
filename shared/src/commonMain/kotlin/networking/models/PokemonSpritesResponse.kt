package networking.models// To parse the JSON, install kotlin's serialization plugin and do:


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpritesResponse(
    val sprites: Sprites
)

@Serializable
data class Sprites(
    @SerialName("back_default")
    val backDefault: String,

    @SerialName("back_shiny")
    val backShiny: String,


    @SerialName("front_default")
    val frontDefault: String,

    @SerialName("front_shiny")
    val frontShiny: String,
)