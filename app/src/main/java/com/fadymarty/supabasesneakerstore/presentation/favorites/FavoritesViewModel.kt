package com.fadymarty.supabasesneakerstore.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.supabasesneakerstore.common.util.Resource
import com.fadymarty.supabasesneakerstore.domain.use_case.sneakers.GetFavoriteSneakers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteSneakers: GetFavoriteSneakers
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state = _state.asStateFlow()

    fun getFavoriteSneakers() {
        getFavoriteSneakers.execute().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FavoritesState(
                        sneakersList = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = FavoritesState(
                        error = result.message ?: "Failed to load favorite sneakers"
                    )
                }

                is Resource.Loading -> {
                    _state.value = FavoritesState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}