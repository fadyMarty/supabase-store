package com.fadymarty.supabasesneakerstore.presentation.details

import com.fadymarty.supabasesneakerstore.data.remote.dto.SneakersDto
import com.fadymarty.supabasesneakerstore.domain.model.Sneakers

data class DetailsState(
    val sneakers: Sneakers? = null,
    val error: String = "",
    val isLoading: Boolean = false
)