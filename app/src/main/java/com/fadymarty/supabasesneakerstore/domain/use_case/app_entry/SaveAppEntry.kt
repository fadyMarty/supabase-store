package com.fadymarty.supabasesneakerstore.domain.use_case.app_entry

import com.fadymarty.supabasesneakerstore.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {

    suspend fun execute() {
        localUserManager.saveAppEntry()
    }
}