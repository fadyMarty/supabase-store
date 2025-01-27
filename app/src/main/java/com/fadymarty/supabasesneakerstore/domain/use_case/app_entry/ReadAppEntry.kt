package com.fadymarty.supabasesneakerstore.domain.use_case.app_entry

import com.fadymarty.supabasesneakerstore.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {

    fun execute(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}