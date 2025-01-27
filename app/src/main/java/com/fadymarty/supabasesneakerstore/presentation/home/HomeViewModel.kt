package com.fadymarty.supabasesneakerstore.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.supabasesneakerstore.common.util.Resource
import com.fadymarty.supabasesneakerstore.domain.use_case.sneakers.AddSneakersToFavorites
import com.fadymarty.supabasesneakerstore.domain.use_case.sneakers.GetSneakers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSneakers: GetSneakers,
    private val addSneakersToFavorites: AddSneakersToFavorites
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getSneakers()
    }

    fun getSneakers() {
        getSneakers.execute().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeState(
                        sneakersList = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = HomeState(
                        error = result.message ?: "Failed to load sneakers"
                    )
                }

                is Resource.Loading -> {
                    _state.value = HomeState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}