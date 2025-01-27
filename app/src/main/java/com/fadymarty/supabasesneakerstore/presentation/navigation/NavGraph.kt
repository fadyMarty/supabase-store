package com.fadymarty.supabasesneakerstore.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fadymarty.supabasesneakerstore.presentation.onboarding.OnboardingScreen
import com.fadymarty.supabasesneakerstore.presentation.splash.SplashScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.MainNavigation.route
        ) {
            MainNavigation()
        }
    }
}