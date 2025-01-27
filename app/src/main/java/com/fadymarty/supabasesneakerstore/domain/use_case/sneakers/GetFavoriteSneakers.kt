package com.fadymarty.supabasesneakerstore.domain.use_case.sneakers

import com.fadymarty.supabasesneakerstore.common.util.Resource
import com.fadymarty.supabasesneakerstore.data.mappers.toSneakers
import com.fadymarty.supabasesneakerstore.domain.model.Sneakers
import com.fadymarty.supabasesneakerstore.domain.repository.SneakersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteSneakers @Inject constructor(
    private val repository: SneakersRepository,
) {

    fun execute(): Flow<Resource<List<Sneakers>>> = flow {
        try {
            emit(Resource.Loading())
            val sneakers = repository.getFavoriteSneakers().map { it.toSneakers() }
            emit(Resource.Success(sneakers))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Failed to load favorite sneakers"))
        }
    }
}