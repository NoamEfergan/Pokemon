package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import networking.models.ListPokemonItem
import networking.models.Pokemon
import networking.models.PokemonApiResponse
import networking.models.PokemonDetails
import networking.models.PokemonResult
import networking.models.PokemonSpritesResponse

class PokemonFetcher {
    private var offset = 0
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchPokemonList(): List<ListPokemonItem> {
        val objects = fetchPokemonObjects()
        val sprites = objects.map { getSpriteURL(it.url) }
        return objects.zip(sprites).map { (obj, sprite) ->
            ListPokemonItem(obj.name, sprite)
        }
    }

    suspend fun fetchPokemonDetails(id: String): PokemonDetails {
        val response: Pokemon = client.get(Endpoints.POKEMON.route + id).body()
        return PokemonDetails(
            name = response.name,
            id = response.id.toInt(),
            mainImageURL = response.sprites.frontDefault,
            otherFormsImageUrl = listOf(response.sprites.backDefault, response.sprites.frontShiny, response.sprites.backShiny),
            types = response.types
        )
    }

    private suspend fun fetchPokemonObjects(): List<PokemonResult> {
        val apiRoute: String = Endpoints.POKEMON.route + "?offset=$offset&limit=20"
        val response: PokemonApiResponse = client.get(apiRoute).body()
        offset += 20
        return response.results
    }

    private suspend fun getSpriteURL(url: String): String {
        val response: PokemonSpritesResponse = client.get(url).body()
        return response.sprites.frontDefault
    }
}