//
//  ErrorScreen.swift
//  iosApp
//
//  Created by Noam Efergan on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ErrorScreen: View {
    let errorMsg: String
    let onRetry: () -> Void

    var body: some View {
        VStack(spacing: 18) {
            Text("Something went wrong!")
                .font(.system(size: 22, weight: .semibold, design: .default))
                .italic()
                .padding(.bottom, 6)

            Text(errorMsg)
                .font(.body)
                .padding(.bottom, 18)

            Button(action: onRetry) {
                Text("Retry")
                    .fontWeight(.medium)
                    .foregroundColor(.white)
                    .padding(.horizontal, 16)
                    .padding(.vertical, 10)
            }
            .background(Color.blue)
            .cornerRadius(4)
        }
    }
}

#Preview {
    ErrorScreen(errorMsg: "Whoopsie", onRetry: {})
}
