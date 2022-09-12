package io.redandroid.westerosandbeyond.local.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.redandroid.westerosandbeyond.local.modules.house.HouseLocalImpl
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindHouseLocal(
        houseLocalImpl: HouseLocalImpl
    ): HouseLocal
}