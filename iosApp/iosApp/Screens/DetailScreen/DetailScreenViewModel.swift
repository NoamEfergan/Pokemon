import Foundation
import shared

// MARK: - DetailScreenViewModel

final class DetailScreenViewModel: ObservableObject {
    private let fetcher: PokemonFetcher = .init()
    @Published var loadingState: LoadingState
    let url: String
    init(loadingState: LoadingState = .loading, url: String) {
        self.loadingState = loadingState
        self.url = url
    }

    @MainActor
    func loadPokemon() async {
        do {
            let pokemon = try await fetcher.fetchPokemonDetails(url: url)
            loadingState = .success(details: pokemon)
        } catch {
            loadingState = .error(msg: error.localizedDescription)
        }
    }
}

// MARK: DetailScreenViewModel.LoadingState

extension DetailScreenViewModel {
    enum LoadingState {
        case loading
        case success(details: PokemonDetails)
        case error(msg: String)
    }
}
