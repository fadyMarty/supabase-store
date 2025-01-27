package com.fadymarty.supabasesneakerstore.presentation.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fadymarty.supabasesneakerstore.domain.model.bottom_navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(containerColor = Color.Black) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                icon = {
                    Icon(
                        imageVector = if (selectedItem == index) item.selectedIcon
                        else item.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.label
                    )
                },
                onClick = { onItemClick(index) },
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