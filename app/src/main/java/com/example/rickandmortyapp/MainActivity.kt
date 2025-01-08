package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmortyapp.presentation.screens.detail.DetailScreen
import com.example.rickandmortyapp.presentation.screens.home.HomeScreen
import com.example.rickandmortyapp.presentation.screens.search.SearchScreen
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                RickAndMortyApp()
            }
        }
    }
}

@Composable
fun RickAndMortyApp() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavigation(navController = navController)
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToDetail = { characterId ->
                    navController.navigate("detail/$characterId")
                },
                onNavigateToSearch = {
                    navController.navigate("search")
                }
            )
        }
        composable(
            "detail/{characterId}",
            arguments = listOf(navArgument("characterId") { defaultValue = 1 })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull() ?: 1
            DetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("search") {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onCharacterClick = { characterId ->
                    navController.navigate("detail/$characterId")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyAppTheme {
        RickAndMortyApp()
    }
}
