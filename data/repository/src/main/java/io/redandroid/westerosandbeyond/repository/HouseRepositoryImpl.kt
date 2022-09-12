package io.redandroid.westerosandbeyond.repository

import io.redandroid.westerosandbeyond.core.DefaultDispatcherProvider
import io.redandroid.westerosandbeyond.core.DispatcherProvider
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HouseRepositoryImpl @Inject constructor(
    private val remote: HouseRemote,
    private val local: HouseLocal,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
): HouseRepository {

    override suspend fun getHouses(): List<House> = withContext(dispatcher.io) {
        val response = remote.fetchPagedHouses(1)

        if (response is RemoteResult.Success) {
            val pagedHouses = response.data
            val houses = pagedHouses.houses

            if (houses.isNotEmpty()) {
                local.saveHouses(houses)
            }

            houses
        } else {

            listOf()
        }
    }

    override suspend fun getHouse(houseUrl: String): House? = withContext(dispatcher.io) {
        local.loadHouse(houseUrl)
    }
}