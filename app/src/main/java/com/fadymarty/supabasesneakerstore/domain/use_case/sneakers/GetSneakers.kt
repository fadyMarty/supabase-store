package com.fadymarty.supabasesneakerstore.domain.use_case.sneakers

import com.fadymarty.supabasesneakerstore.common.util.Resource
import com.fadymarty.supabasesneakerstore.data.mappers.toSneakers
import com.fadymarty.supabasesneakerstore.domain.model.Sneakers
import com.fadymarty.supabasesneakerstore.domain.repository.SneakersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSneakers @Inject constructor(
    private val repository: SneakersRepository,
) {
    fun execute(): Flow<Resource<List<Sneakers>>> = flow {
        try {
            emit(Resource.Loading())
            val sneakersList = repository.getSneakers().map { it.toSneakers() }
            emit(Resource.Success(sneakersList))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Failed to load sneakers"))
        }
    }
}