//package com.example.rickandmortyapp.presentation.components
//
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//import androidx.navigation.NavController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import com.example.rickandmortyapp.presentation.navigation.BottomNavItem
//
//@Composable
//fun BottomNavigationBar(navController: NavController, items: List<BottomNavItem>) {
//    NavigationBar(
//        containerColor = Color.White
//    ) {
//        val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
//        items.forEach { item ->
//            NavigationBarItem(
//                selected = currentRoute == item.route,
//                onClick = { navController.navigate(item.route) },
//                label = { androidx.compose.material3.Text(text = item.title) },
//                icon = { androidx.compose.material3.Icon(item.icon, contentDescription = null) }
//            )
//        }
//    }
//}
