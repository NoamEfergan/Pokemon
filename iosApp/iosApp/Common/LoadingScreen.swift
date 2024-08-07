//
//  LoadingScreen.swift
//  iosApp
//
//  Created by Noam Efergan on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoadingScreen: View {
    var body: some View {
        VStack {
            Text("Loading")
                .font(.title)
                .fontWeight(.bold)
                .italic()
            ProgressView()
                .progressViewStyle(.circular)
        }
    }
}

#Preview {
    LoadingScreen()
}
