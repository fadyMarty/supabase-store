package com.fadymarty.supabasesneakerstore.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fadymarty.supabasesneakerstore.domain.model.bottom_navigation.bottomNavigationItems
import com.fadymarty.supabasesneakerstore.domain.model.navigation_drawer.NavigationDrawerState
import com.fadymarty.supabasesneakerstore.domain.model.navigation_drawer.isOpened
import com.fadymarty.supabasesneakerstore.domain.model.navigation_drawer.opposite
import com.fadymarty.supabasesneakerstore.presentation.details.DetailsScreen
import com.fadymarty.supabasesneakerstore.presentation.favorites.FavoritesScreen
import com.fadymarty.supabasesneakerstore.presentation.home.HomeScreen
import com.fadymarty.supabasesneakerstore.presentation.navigation_drawer.NavigationDrawer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {

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

    val isBarsVisible = remember(backStackState) {
        backStackState?.destination?.route == Screen.Home.route ||
                backStackState?.destination?.route == Screen.Favorites.route
    }

    var drawerState by remember {
        mutableStateOf(NavigationDrawerState.Closed)
    }

    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) 241.dp else 0.dp
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.76f else 1f
    )
    val animatedRotation by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) -3.43f else 0f
    )
    val animatedCornerRadius by animateDpAsState(
        targetValue = if (drawerState.isOpened()) 25.dp else 0.dp
    )

    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = NavigationDrawerState.Closed
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavigationDrawer()

        Scaffold(
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .rotate(animatedRotation)
                .clip(RoundedCornerShape(animatedCornerRadius))
                .clickable(enabled = drawerState == NavigationDrawerState.Opened) {
                    drawerState = NavigationDrawerState.Closed
                },
            topBar = {
                if (isBarsVisible) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text("Sneaker Store")
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    drawerState = drawerState.opposite()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = null
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black,
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    )
                }
            },
            bottomBar = {
                if (isBarsVisible) {
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
                startDestination = Screen.Home.route
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