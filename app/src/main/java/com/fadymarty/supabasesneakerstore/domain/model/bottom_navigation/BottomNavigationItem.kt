package com.fadymarty.supabasesneakerstore.domain.model.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
)

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