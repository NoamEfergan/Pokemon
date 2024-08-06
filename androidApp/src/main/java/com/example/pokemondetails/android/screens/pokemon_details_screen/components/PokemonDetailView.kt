package com.example.pokemondetails.android.screens.pokemon_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemondetails.android.R
import networking.models.PokemonDetails
import utils.Formatter

@Composable
fun PokemonDetailView(
    modifier: Modifier = Modifier,
    pokemon: PokemonDetails
) {
    Column(modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text(pokemon.name.capitalize(Locale.current), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(Formatter.padToThreeDigits(pokemon.id), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Light)
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
            ) {
                pokemon.types.forEach {
                    Text(it.type.name.capitalize(Locale.current), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Light)
                }
            }
        }
        Card(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = pokemon.mainImageURL,
                contentDescription = "An image of the pokemon ${pokemon.name}",
                placeholder = painterResource(id = R.drawable.ic_pokeball),
                modifier = Modifier
                    .padding(16.dp)
                    .size(128.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
        ) {
            pokemon.otherFormsImageUrl.forEach {
                Card {
                    AsyncImage(
                        model = pokemon.mainImageURL,
                        contentDescription = "An image of the pokemon ${pokemon.name}",
                        placeholder = painterResource(id = R.drawable.ic_pokeball),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(32.dp)
                    )
                }

            }
        }
    }
}

@Preview(name = "PokemonDetailView", showBackground = true)
@Composable
private fun PreviewPokemonDetailView() {
    PokemonDetailView(
        pokemon = PokemonDetails.mockPokemonDetails,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    )
}