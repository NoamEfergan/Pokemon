package com.example.pokemondetails.android.screens.homescreen

import AutoResizeText
import FontSizeRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokemondetails.android.R
import networking.models.ListPokemonItem

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    pokemon: ListPokemonItem,
    onItemClick: (ListPokemonItem) -> Unit = {},
) {
    ElevatedCard(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick(pokemon) }
        .padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pokemon.iconURL,
                contentDescription = "An image of the pokemon ${pokemon.name}",
                placeholder = painterResource(id = R.drawable.ic_pokeball),
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            AutoResizeText(
                text = pokemon.name.capitalize(Locale.current),
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                fontSizeRange = FontSizeRange(
                    min = 10.sp,
                    max = 12.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(name = "PokemonListItem", showBackground = true)
@Composable
private fun PreviewPokemonListItem() {
    PokemonListItem(
        pokemon = ListPokemonItem(
            name = "Pikachu",
            iconURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
        )
    )
}