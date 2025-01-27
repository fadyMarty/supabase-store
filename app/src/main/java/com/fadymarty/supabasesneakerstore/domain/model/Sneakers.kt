package com.fadymarty.supabasesneakerstore.domain.model

data class Sneakers(
    val id: Int,
    val name: String,
    val title: String,
    val description: String,
    val price: Float,
    val imageUrl: String,
    val inFavorites: Boolean
)