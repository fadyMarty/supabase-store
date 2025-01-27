package com.fadymarty.supabasesneakerstore.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.supabasesneakerstore.domain.use_case.app_entry.ReadAppEntry
import com.fadymarty.supabasesneakerstore.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readAppEntry: ReadAppEntry,
) : ViewModel() {

    private val _startDestination = MutableStateFlow(Screen.Onboarding.route)
    val startDestination = _startDestination.asStateFlow()

    init {
        readAppEntry.execute().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                _startDestination.value = Screen.MainNavigation.route
            } else {
                _startDestination.value = Screen.Onboarding.route
            }
        }.launchIn(viewModelScope)
    }
}