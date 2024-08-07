import Foundation
import shared
import SwiftUI

// MARK: - HomeScreenViewModel

final class HomeScreenViewModel: ObservableObject {
  private let fetcher: PokemonFetcher = .init()
  private var pokemonList: [ListPokemonItem] = []

  @Published private(set) var loadingState: LoadingState = .initialLoading

  @MainActor
  func loadInitialPokemon() async {
    do {
      print("Noam: Here starting to load")
      let fetchedPokemonList = try await fetcher.fetchPokemonList()
      pokemonList.append(contentsOf: fetchedPokemonList)
      loadingState = .content(pokemon: pokemonList, isLoadingMore: false)
      print("Noam: loaded")
    } catch {
      loadingState = .error(msg: error.localizedDescription)
    }
  }

  @MainActor
  func loadMorePokemon() async {
    switch loadingState {
    case let .content(pokemon, isLoadingMore):
      if !isLoadingMore {
        loadingState = .content(pokemon: pokemon, isLoadingMore: true)
        await loadInitialPokemon()
      }
    default:
      return
    }
  }
}

// MARK: HomeScreenViewModel.LoadingState

extension HomeScreenViewModel {
  enum LoadingState {
    case initialLoading
    case content(pokemon: [ListPokemonItem], isLoadingMore: Bool = false)
    case error(msg: String)
  }
}
