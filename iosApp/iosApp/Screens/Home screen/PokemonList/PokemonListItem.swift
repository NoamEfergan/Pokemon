//
//  PokemonListItem.swift
//  iosApp
//
//  Created by Noam Efergan on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct PokemonListItem: View {
    let pokemon: ListPokemonItem
    private let imageSize: CGFloat = 64

    var body: some View {
        HStack {
            PokeballPlaceholderImage(url: pokemon.iconURL)
                .frame(width: imageSize, height: imageSize)

            Text(pokemon.name)
                .font(.caption)
        }
        .cardViewModifier()
    }
}

#Preview {
    PokemonListItem(pokemon: .companion.list.first!)
}
