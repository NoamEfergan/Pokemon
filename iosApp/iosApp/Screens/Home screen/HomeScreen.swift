//
//  HomeScreen.swift
//  iosApp
//
//  Created by Noam Efergan on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct HomeScreen: View {
    @StateObject private var viewModel = HomeScreenViewModel()
    var body: some View {
        NavigationStack {
            switch viewModel.loadingState {
            case .initialLoading:
                LoadingScreen()
            case let .content(pokemon, isLoadingMore):
                PokemonListView(pokemon: pokemon, isLoadingMore: isLoadingMore)
            case let .error(msg):
                ErrorScreen(errorMsg: msg) {
                    Task {
                        viewModel.loadInitialPokemon
                    }
                }
            }
        }
        .task {
            await viewModel.loadInitialPokemon()
        }
    }
}

#Preview {
    HomeScreen()
}
