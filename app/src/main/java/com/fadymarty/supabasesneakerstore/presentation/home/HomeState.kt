package com.fadymarty.supabasesneakerstore.presentation.home

import com.fadymarty.supabasesneakerstore.domain.model.Sneakers

data class HomeState(
    val sneakersList: List<Sneakers> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)