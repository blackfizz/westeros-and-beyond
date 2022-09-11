package io.redandroid.westerosandbeyond.repository

import io.redandroid.westerosandbeyond.core.DefaultDispatcherProvider
import io.redandroid.westerosandbeyond.core.DispatcherProvider
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository

class HouseRepositoryImpl(
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
): HouseRepository {
}