//
//  PokeballPlaceholderImage.swift
//  iosApp
//
//  Created by Noam Efergan on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct PokeballPlaceholderImage: View {
    let url: String
    var body: some View {
        AsyncImage(url: URL(string: url)) { image in
            image
                .resizable()
        } placeholder: {
            Image(.icPokeball)
                .resizable()
        }
    }
}

#Preview {
    PokeballPlaceholderImage(url: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")
}
