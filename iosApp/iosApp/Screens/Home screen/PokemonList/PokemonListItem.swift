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
    let onItemClick: (ListPokemonItem) -> Void
    private let imageSize: CGFloat = 64

    var body: some View {
        Button(action: { onItemClick(pokemon) }) {
            HStack {
                AsyncImage(url: URL(string: pokemon.iconURL)) { image in
                    image
                        .resizable()
                } placeholder: {
                    Image(.icPokeball)
                        .resizable()
                }
                .frame(width: imageSize, height: imageSize)

                Text(pokemon.name)
                    .font(.caption)
            }
        }
        .tint(.black)
        .padding()
        .background(Color.gray.opacity(0.1))
        .cornerRadius(8)
    }
}

#Preview {
    PokemonListItem(pokemon: .companion.list.first!) { pokemon in
        print("Clicked on : \(pokemon)")
    }
}
