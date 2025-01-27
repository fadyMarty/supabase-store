package com.fadymarty.supabasesneakerstore.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.supabasesneakerstore.common.util.Resource
import com.fadymarty.supabasesneakerstore.domain.use_case.sneakers.AddSneakersToFavorites
import com.fadymarty.supabasesneakerstore.domain.use_case.sneakers.GetSneakersById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSneakersById: GetSneakersById,
    private val addSneakersToFavorites: AddSneakersToFavorites,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun getSneakersById(id: Int) {
        getSneakersById.execute(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DetailsState(
                        sneakers = result.data
                    )
                }

                is Resource.Error -> {
                    _state.value = DetailsState(
                        error = result.message ?: "Failed to load sneakers by id"
                    )
                }

                is Resource.Loading -> {
                    _state.value = DetailsState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addSneakersToFavorites() {
        viewModelScope.launch {
            addSneakersToFavorites.execute(
                id = _state.value.sneakers?.id ?: 0,
                inFavorites = !_state.value.sneakers?.inFavorites!!
            )
            _state.value = DetailsState(
                sneakers = _state.value.sneakers?.copy(
                    inFavorites = !_state.value.sneakers?.inFavorites!!
                )
            )
        }
    }
}