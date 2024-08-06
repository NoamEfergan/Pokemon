import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokemondetails.android.screens.homescreen.PokemonListItem
import networking.models.ListPokemonItem

private const val buffer = 2

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    pokemonList: List<ListPokemonItem>,
    onItemClick: (ListPokemonItem) -> Unit = {},
    loadMore: () -> Unit = {}
) {
    val gridState = rememberLazyGridState()
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == gridState.layoutInfo.totalItemsCount - buffer
        }
    }
    LaunchedEffect(reachedBottom) {
        if (reachedBottom) loadMore()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        state = gridState,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = pokemonList,
            key = { card: ListPokemonItem -> card.iconURL }
        ) { card ->
            PokemonListItem(pokemon = card, onItemClick = onItemClick)
        }
    }
}

@Preview(name = "PokemonList", showBackground = true)
@Composable
private fun PreviewPokemonList() {
    PokemonListScreen(pokemonList = ListPokemonItem.Mock.list)
}