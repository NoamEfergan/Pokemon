package networking

import kotlinx.serialization.Serializable


@Serializable
data class PokemonApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

@Serializable
data class PokemonResult(
    val name: String,
    val url: String
)