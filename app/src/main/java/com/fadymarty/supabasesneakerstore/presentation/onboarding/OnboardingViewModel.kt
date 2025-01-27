package com.fadymarty.supabasesneakerstore.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.supabasesneakerstore.domain.use_case.app_entry.SaveAppEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveAppEntry: SaveAppEntry,
) : ViewModel() {

    fun saveAppEntry() {
        viewModelScope.launch {
            saveAppEntry.execute()
        }
    }
}