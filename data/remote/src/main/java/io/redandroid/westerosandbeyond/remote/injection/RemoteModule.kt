package io.redandroid.westerosandbeyond.remote.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.redandroid.westerosandbeyond.remote.modules.house.HouseRemoteImpl
import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindHouseRemote(
        houseRemoteImpl: HouseRemoteImpl
    ): HouseRemote
}