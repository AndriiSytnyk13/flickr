package com.sytyy.compose.practice.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sytyy.compose.practice.ui.screen.DetailScreen
import com.sytyy.compose.practice.ui.screen.ListScreen
import com.sytyy.compose.practice.ui.screen.PhotosViewModel

@Composable
fun NavigationGraph(navHostController: NavHostController = rememberNavController()) {

    val photosViewModel: PhotosViewModel = hiltViewModel()
    NavHost(
        navController = navHostController,
        startDestination = NavigationScreen.ListScreen.routeName
    ) {
        composable(route = NavigationScreen.ListScreen.routeName) {
            ListScreen(navHostController, photosViewModel)
        }
        composable(route = NavigationScreen.DetailScreen.routeName) {
            DetailScreen(photosViewModel)
        }
    }
}

fun NavHostController.navigateFromListScreenToDetailScreen() {
    navigate(NavigationScreen.DetailScreen.routeName) {
        launchSingleTop = true
    }
}