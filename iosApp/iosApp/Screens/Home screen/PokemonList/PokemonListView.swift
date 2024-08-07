import shared
import SwiftUI

struct PokemonListView: View {
    let pokemon: [ListPokemonItem]
    let isLoadingMore: Bool
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 16) { // Can be done with Array repeating too
                ForEach(pokemon, id: \.name) { pokemon in
                    NavigationLink(destination: {
                        DetailScreen(viewModel: .init(url: pokemon.passThroughURL))
                    }, label: {
                        PokemonListItem(pokemon: pokemon)
                            .tint(.black)
                    })
                }
            }
            ProgressView("Loading more...")
        }
    }
}

#Preview {
    PokemonListView(pokemon: ListPokemonItem.companion.list, isLoadingMore: true)
        .previewLayout(.sizeThatFits)
}
