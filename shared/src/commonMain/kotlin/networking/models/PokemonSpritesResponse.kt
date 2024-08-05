package networking.models// To parse the JSON, install kotlin's serialization plugin and do:


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

@Serializable
data class PokemonSpritesResponse(
    val sprites: Sprites
)

@Serializable
data class Sprites(
    @SerialName("back_default")
    val backDefault: String,

    @SerialName("back_female")
    val backFemale: JsonElement? = null,

    @SerialName("back_shiny")
    val backShiny: String,

    @SerialName("back_shiny_female")
    val backShinyFemale: JsonElement? = null,

    @SerialName("front_default")
    val frontDefault: String,

    @SerialName("front_female")
    val frontFemale: JsonElement? = null,

    @SerialName("front_shiny")
    val frontShiny: String,

    @SerialName("front_shiny_female")
    val frontShinyFemale: JsonElement? = null,
)