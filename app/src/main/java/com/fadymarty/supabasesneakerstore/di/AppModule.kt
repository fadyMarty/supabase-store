package com.fadymarty.supabasesneakerstore.di

import android.app.Application
import android.content.Context
import com.fadymarty.supabasesneakerstore.common.util.Constants.SUPABASE_KEY
import com.fadymarty.supabasesneakerstore.common.util.Constants.SUPABASE_URL
import com.fadymarty.supabasesneakerstore.data.manager.LocalUserManagerImpl
import com.fadymarty.supabasesneakerstore.data.repository.SneakersRepositoryImpl
import com.fadymarty.supabasesneakerstore.domain.manager.LocalUserManager
import com.fadymarty.supabasesneakerstore.domain.repository.SneakersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSneakersRepository(client: SupabaseClient): SneakersRepository {
        return SneakersRepositoryImpl(client = client)
    }

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(context = application)
    }

    @Provides
    @Singleton
    fun providesSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Postgrest)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }
}