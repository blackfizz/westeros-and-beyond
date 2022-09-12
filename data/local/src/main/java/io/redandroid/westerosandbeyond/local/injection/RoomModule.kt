package io.redandroid.westerosandbeyond.local.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.redandroid.westerosandbeyond.local.core.WesterosAndBeyondDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = Room
        .databaseBuilder(
            appContext,
            WesterosAndBeyondDatabase::class.java,
            "westerosAndBeyondDatabase"
        )
        .build()
}