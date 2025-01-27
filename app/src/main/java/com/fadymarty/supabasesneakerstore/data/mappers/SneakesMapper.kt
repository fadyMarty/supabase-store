package com.fadymarty.supabasesneakerstore.data.mappers

import com.fadymarty.supabasesneakerstore.data.remote.dto.SneakersDto
import com.fadymarty.supabasesneakerstore.domain.model.Sneakers

fun SneakersDto.toSneakers(): Sneakers {
    return Sneakers(
        id = id,
        name = name,
        title = title,
        description = description,
        price = price,
        imageUrl = imageUrl,
        inFavorites = inFavorites
    )
}