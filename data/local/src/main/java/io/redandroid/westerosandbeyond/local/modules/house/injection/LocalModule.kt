package io.redandroid.westerosandbeyond.local.modules.house.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.redandroid.westerosandbeyond.local.modules.house.HouseLocalImpl
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

//    @Provides
//    @Singleton
//    fun provideRoomDatabase() : Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Endpoints.baseUrl)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//    }

    @Binds
    abstract fun bindHouseLocal(
        houseLocalImpl: HouseLocalImpl
    ): HouseLocal
}