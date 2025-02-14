package com.fadymarty.supabasesneakerstore.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Onboarding : Screen("onboarding_screen")

    object Home : Screen("home_screen")
    object Favorites : Screen("favorites_screen")
    object Details : Screen("details_screen")

    object MainNavigation : Screen("main_screen")
}