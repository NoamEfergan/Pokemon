import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
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
    loadMore: () -> Unit = {},
    isLoadingMore: Boolean = false
) {
    val gridState = rememberLazyGridState()

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

        item(span = { GridItemSpan(2) }) {
            if (isLoadingMore) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastIndex ->
                if (lastIndex != null && lastIndex >= pokemonList.size - buffer) {
                    loadMore()
                }
            }
    }
}

@Preview(name = "PokemonList", showBackground = true)
@Composable
private fun PreviewPokemonList() {
    PokemonListScreen(pokemonList = ListPokemonItem.Mock.list)
}