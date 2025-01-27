package com.fadymarty.supabasesneakerstore.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fadymarty.supabasesneakerstore.presentation.details.DetailsScreen
import com.fadymarty.supabasesneakerstore.presentation.favorites.FavoritesScreen
import com.fadymarty.supabasesneakerstore.presentation.home.HomeScreen

@Composable
fun MainNavigation() {

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            selectedIcon = Icons.Rounded.Home,
            unselectedIcon = Icons.Outlined.Home,
            label = "Home"
        ),
        BottomNavigationItem(
            selectedIcon = Icons.Rounded.Favorite,
            unselectedIcon = Icons.Rounded.FavoriteBorder,
            label = "Favorites"
        )
    )

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(backStackState) {
        when (backStackState?.destination?.route) {
            Screen.Home.route -> 0
            Screen.Favorites.route -> 1
            else -> 0
        }
    }
    val isBottomBarVisible = remember(backStackState) {
        backStackState?.destination?.route == Screen.Home.route ||
                backStackState?.destination?.route == Screen.Favorites.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NavigationBar(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ) {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            icon = {
                                Icon(
                                    imageVector = if (selectedItem == index) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = item.label
                                )
                            },
                            onClick = {
                                when (index) {
                                    0 -> navigateToTap(
                                        navController = navController,
                                        route = Screen.Home.route
                                    )

                                    1 -> navigateToTap(
                                        navController = navController,
                                        route = Screen.Favorites.route
                                    )
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White,
                                indicatorColor = Color.DarkGray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startDestination = Screen.Home.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    topPadding = innerPadding.calculateTopPadding()
                )
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    navController = navController,
                    topPadding = innerPadding.calculateTopPadding()
                )
            }

            composable(
                route = Screen.Details.route + "/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) { entry ->
                val id = entry.arguments?.getInt("id") ?: 0
                DetailsScreen(
                    id = id
                )
            }
        }
    }
}

private fun navigateToTap(
    navController: NavHostController,
    route: String,
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

data class BottomNavigationItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
)