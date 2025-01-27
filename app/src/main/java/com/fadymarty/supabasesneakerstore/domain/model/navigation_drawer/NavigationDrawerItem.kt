package com.fadymarty.supabasesneakerstore.domain.model.navigation_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val drawerNavigationItems = listOf(
    DrawerNavigationItem(
        title = "Home",
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    DrawerNavigationItem(
        title = "Favorites",
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    ),
    DrawerNavigationItem(
        title = "Cart",
        selectedIcon = Icons.Rounded.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart
    ),
    DrawerNavigationItem(
        title = "Settings",
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
)