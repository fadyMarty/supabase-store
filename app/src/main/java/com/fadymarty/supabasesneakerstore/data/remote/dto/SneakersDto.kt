package com.fadymarty.supabasesneakerstore.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SneakersDto(
    val id: Int,
    val name: String,
    val title: String,
    val description: String,
    val price: Float,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("in_favorites")
    val inFavorites: Boolean
)