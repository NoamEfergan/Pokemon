package com.example.pokemondetails

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform