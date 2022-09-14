package io.redandroid.westerosandbeyond.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.redandroid.westerosandbeyond.core.disptacher.DefaultDispatcherProvider
import io.redandroid.westerosandbeyond.core.disptacher.DispatcherProvider

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DefaultDispatcherProvider
    ): DispatcherProvider
}