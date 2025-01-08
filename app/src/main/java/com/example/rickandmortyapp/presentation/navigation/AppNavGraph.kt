//package com.example.rickandmortyapp.presentation.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.rickandmortyapp.presentation.screens.detail.DetailScreen
//import com.example.rickandmortyapp.presentation.screens.HomeScreen
//
//@Composable
//fun AppNavGraph(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
//        composable(route = "home") {
//            HomeScreen(onCharacterClick = { character ->
//                navController.navigate("detail/${character.id}")
//            })
//        }
//
//        composable(route = "detail/{characterId}") { backStackEntry ->
//            val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: 0
//            DetailScreen(characterId = characterId)
//        }
//
//    }
//}
