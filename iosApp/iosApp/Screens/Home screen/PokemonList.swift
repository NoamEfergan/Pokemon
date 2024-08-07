//
//  PokemonList.swift
//  iosApp
//
//  Created by Noam Efergan on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

// MARK: - PokemonList

struct PokemonList: View {
    let pokemonList: [ListPokemonItem]
    let isLoadingMore: Bool
    let hasReachLast: () -> Void
    let onItemClick: (ListPokemonItem) -> Void

    private let columns = [GridItem(.flexible()), GridItem(.flexible())]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 8) {
                ForEach(pokemonList, id: \.iconURL) { pokemon in
                    PokemonListItem(pokemon: pokemon, onItemClick: onItemClick)
                }

                if viewModel.loadingState.isLoadingMore {
                    Section(footer: ProgressView()) {
                        EmptyView()
                    }
                    .frame(maxWidth: .infinity)
                }
            }
            .padding(8)
        }
    }
}

// MARK: - PokemonListItem

struct PokemonListItem: View {
    let pokemon: ListPokemonItem
    let onItemClick: (ListPokemonItem) -> Void

    var body: some View {
        Button(action: { onItemClick(pokemon) }) {
            VStack {
                AsyncImage(url: URL(string: pokemon.iconURL)) { image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .frame(width: 100, height: 100)

                Text(pokemon.name)
                    .font(.caption)
            }
        }
        .frame(maxWidth: .infinity)
        .padding()
        .background(Color.gray.opacity(0.1))
        .cornerRadius(8)
    }
}

extension HomeScreenViewModel.LoadingState {
    var pokemonList: [ListPokemonItem] {
        switch self {
        case let .content(pokemon, _):
            return pokemon
        default:
            return []
        }
    }

    var isLoadingMore: Bool {
        switch self {
        case let .content(_, isLoadingMore):
            return isLoadingMore
        default:
            return false
        }
    }

    var shouldLoadMore: Bool {
        switch self {
        case let .content(pokemon, isLoadingMore):
            return !pokemon.isEmpty && !isLoadingMore
        default:
            return false
        }
    }
}

// MARK: - PokemonList_Previews

/// Preview
struct PokemonList_Previews: PreviewProvider {
    static var previews: some View {
        PokemonList(viewModel: HomeScreenViewModel(), onItemClick: { _ in })
    }
}
