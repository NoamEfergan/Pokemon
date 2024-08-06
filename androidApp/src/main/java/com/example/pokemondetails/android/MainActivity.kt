package com.example.pokemondetails.android

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemondetails.android.screens.homescreen.HomeScreen
import com.example.pokemondetails.android.screens.homescreen.HomeScreenViewModel
import com.example.pokemondetails.android.screens.pokemon_details_screen.PokemonDetailScreen
import com.example.pokemondetails.android.screens.pokemon_details_screen.PokemonDetailViewModel
import java.net.URLEncoder

class MainActivity : ComponentActivity() {
    private val homeScreenViewModel = HomeScreenViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeScreenViewModel) { pokemon ->
                                val encodedUrl = URLEncoder.encode(pokemon.passThroughURL, "UTF-8")
                                navController.navigate("details/$encodedUrl")
                            }
                        }
                        composable(
                            route = "details/{encodedUrl}",
                            arguments = listOf(navArgument("encodedUrl") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val encodedUrl = backStackEntry.arguments?.getString("encodedUrl")
                            encodedUrl?.let {
                                val decodedUrl = java.net.URLDecoder.decode(it, "UTF-8")
                                val viewModel = PokemonDetailViewModel(url = decodedUrl)
                                PokemonDetailScreen(viewModel = viewModel, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        HomeScreen(viewModel = HomeScreenViewModel()) {
            Log.i("Noam", "Item clicked: $it")
        }
    }
}