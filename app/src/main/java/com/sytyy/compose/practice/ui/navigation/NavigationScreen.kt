package com.sytyy.compose.practice.ui.navigation

sealed class NavigationScreen(val routeName: String) {
    data object ListScreen : NavigationScreen("list_screen")
    data object DetailScreen : NavigationScreen("detail_screen")
}