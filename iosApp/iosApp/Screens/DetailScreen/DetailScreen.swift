//
//  DetailScreen.swift
//  iosApp
//
//  Created by Noam Efergan on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct DetailScreen: View {
    @ObservedObject var viewModel: DetailScreenViewModel
    private let imageSize: CGFloat = 128
    var body: some View {
        switch viewModel.loadingState {
        case .loading:
            LoadingScreen()
                .task {
                    await viewModel.loadPokemon()
                }
        case let .success(details):
            pokemonDetails(pokemon: details)
        case let .error(msg):
            ErrorScreen(errorMsg: msg) {
                Task {
                    await viewModel.loadPokemon()
                }
            }
        }
    }

    @ViewBuilder
    private func pokemonDetails(pokemon: PokemonDetails) -> some View {
        VStack {
            VStack {
                Text(Formatter.shared.padToThreeDigits(number: pokemon.id))
                HStack {
                    ForEach(pokemon.types, id: \.slot) { type in
                        Text(type.type.name)
                    }
                }
            }
            .font(.body)
            .fontWeight(.light)

            PokeballPlaceholderImage(url: pokemon.mainImageURL)
                .frame(width: imageSize, height: imageSize)
                .cardViewModifier()

            VStack {
                Text("Other forms")
                    .font(.subheadline)
                    .bold()
                HStack {
                    ForEach(pokemon.otherFormsImageUrl, id: \.self) { url in
                        PokeballPlaceholderImage(url: url)
                            .frame(width: 32, height: 32)
                            .cardViewModifier()
                    }
                }
            }
        }
        .navigationTitle(pokemon.name.capitalized)
    }
}

#Preview {
    NavigationStack {
        DetailScreen(viewModel: .init(loadingState: .success(details: .companion.mockPokemonDetails), url: ""))
    }
}
