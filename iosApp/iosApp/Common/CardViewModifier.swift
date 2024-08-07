import Foundation
import SwiftUI

struct CardViewModifier: ViewModifier {
    func body(content: Content) -> some View {
        content
            .tint(.black)
            .padding()
            .background(Color.gray.opacity(0.1))
            .cornerRadius(8)
    }
}

extension View {
    func cardViewModifier() -> some View {
        modifier(CardViewModifier())
    }
}

// Usage example:
struct ContentView: View {
    var body: some View {
        Button("Tap me") {
            print("Button tapped")
        }
        .cardViewModifier()
    }
}
