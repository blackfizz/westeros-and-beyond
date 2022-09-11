package io.redandroid.westerosandbeyond.repository

import io.redandroid.westerosandbeyond.core.DefaultDispatcherProvider
import io.redandroid.westerosandbeyond.core.DispatcherProvider
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote
import javax.inject.Inject

class HouseRepositoryImpl @Inject constructor(
    private val remote: HouseRemote,
    private val local: HouseLocal,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
): HouseRepository {
}