package com.fadymarty.supabasesneakerstore.domain.repository

import com.fadymarty.supabasesneakerstore.data.remote.dto.SneakersDto

interface SneakersRepository {

    suspend fun getSneakers(): List<SneakersDto>

    suspend fun getSneakersById(id: Int): SneakersDto

    suspend fun addSneakersToFavorites(id: Int, inFavorites: Boolean)

    suspend fun getFavoriteSneakers(): List<SneakersDto>
}