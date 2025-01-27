package com.fadymarty.supabasesneakerstore.domain.model.navigation_drawer

enum class NavigationDrawerState {
    Opened,
    Closed
}

fun NavigationDrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun NavigationDrawerState.opposite(): NavigationDrawerState {
    return if (this == NavigationDrawerState.Opened) NavigationDrawerState.Closed
    else NavigationDrawerState.Opened
}