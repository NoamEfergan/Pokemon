package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokemonFetcher {
    private var offset = 0
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }


    suspend fun fetchPokemon(): List<PokemonResult> {
        val apiRoute: String = Endpoints.POKEMON.route + "?offset=$offset"
        val response: PokemonApiResponse = client.get(apiRoute).body()
        offset += 20
        return response.results
    }
}