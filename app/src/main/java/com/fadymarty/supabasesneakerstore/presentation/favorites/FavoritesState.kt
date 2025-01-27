package com.fadymarty.supabasesneakerstore.presentation.favorites

import com.fadymarty.supabasesneakerstore.domain.model.Sneakers

data class FavoritesState(
    val sneakersList: List<Sneakers> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)