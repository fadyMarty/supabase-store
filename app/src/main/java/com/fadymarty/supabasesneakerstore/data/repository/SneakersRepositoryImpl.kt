package com.fadymarty.supabasesneakerstore.data.repository

import com.fadymarty.supabasesneakerstore.data.remote.dto.SneakersDto
import com.fadymarty.supabasesneakerstore.domain.repository.SneakersRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class SneakersRepositoryImpl @Inject constructor(
    private val client: SupabaseClient,
) : SneakersRepository {

    override suspend fun getSneakers(): List<SneakersDto> {
        return client.from("sneakers")
            .select()
            .decodeList<SneakersDto>()
    }

    override suspend fun getSneakersById(id: Int): SneakersDto {
        return client.from("sneakers")
            .select {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<SneakersDto>()
    }

    override suspend fun addSneakersToFavorites(id: Int, inFavorites: Boolean) {
        client.from("sneakers")
            .update(
                {
                    set("in_favorites", inFavorites)
                }
            ) {
                filter {
                    eq("id", id)
                }
            }
    }

    override suspend fun getFavoriteSneakers(): List<SneakersDto> {
        return client.from("sneakers")
            .select {
                filter {
                    eq("in_favorites", true)
                }
            }.decodeList<SneakersDto>()
    }
}