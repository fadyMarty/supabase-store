package com.fadymarty.supabasesneakerstore.domain.use_case.sneakers

import com.fadymarty.supabasesneakerstore.domain.repository.SneakersRepository
import javax.inject.Inject

class AddSneakersToFavorites @Inject constructor(
    private val sneakersRepository: SneakersRepository,
) {

    suspend fun execute(id: Int, inFavorites: Boolean) {
        try {
            sneakersRepository
                .addSneakersToFavorites(id, inFavorites)
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}