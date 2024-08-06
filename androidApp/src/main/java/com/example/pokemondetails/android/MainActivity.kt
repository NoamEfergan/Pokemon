package com.example.pokemondetails.android

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
import com.example.pokemondetails.android.screens.homescreen.HomeScreen
import com.example.pokemondetails.android.screens.homescreen.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    val homeScreenViewModel = HomeScreenViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel = homeScreenViewModel) {
                        Log.i("Noam", "Item clicked: $it")
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