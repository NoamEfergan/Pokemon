import shared
import SwiftUI

struct PokemonListView: View {
    let pokemon: [ListPokemonItem]
    let isLoadingMore: Bool
    let loadMore: () -> Void
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 16) { // Can be done with Array repeating too
                ForEach(pokemon, id: \.name) { item in
                    NavigationLink(destination: {
                        DetailScreen(viewModel: .init(url: item.passThroughURL))
                    }, label: {
                        PokemonListItem(pokemon: item)
                            .tint(.black)
                    })
                    .task {
                        if item.name == pokemon.last?.name {
                            loadMore()
                        }
                    }
                }
            }
            ProgressView("Loading more...")
        }
    }
}

#Preview {
    PokemonListView(pokemon: ListPokemonItem.companion.list, isLoadingMore: true) {
        print("Load more")
    }
    .previewLayout(.sizeThatFits)
}
