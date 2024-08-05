package networking.models

import kotlinx.serialization.Serializable


@Serializable
data class PokemonApiResponse(
    val results: List<PokemonResult>
)

@Serializable
data class PokemonResult(
    val name: String,
    val url: String
)