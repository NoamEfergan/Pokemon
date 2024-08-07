import shared
import SwiftUI

struct PokemonListView: View {
    let pokemon: [ListPokemonItem]
    let isLoadingMore: Bool
    let onItemClick: (ListPokemonItem) -> Void
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 16) { // Can be done with Array repeating too
                ForEach(pokemon, id: \.name) { pokemon in
                    PokemonListItem(pokemon: pokemon, onItemClick: onItemClick)
                }
            }
            ProgressView("Loading more...")
                .frame(width: .infinity)
        }
    }
}

#Preview {
    PokemonListView(pokemon: ListPokemonItem.companion.list, isLoadingMore: true) { pokemon in
        print("Noam: \(pokemon)")
    }
    .previewLayout(.sizeThatFits)
}
