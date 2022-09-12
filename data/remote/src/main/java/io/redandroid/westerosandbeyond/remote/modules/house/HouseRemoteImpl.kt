package io.redandroid.westerosandbeyond.remote.modules.house

import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import io.redandroid.westerosandbeyond.remote.modules.house.model.asHouse
import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote
import javax.inject.Inject

class HouseRemoteImpl @Inject constructor(
    private val api: HouseApi
): HouseRemote {

    override suspend fun fetchPagedHouses(pageNumber: Int): RemoteResult<PagedHouses> {
        val response = api.fetchHouses(pageNumber)

        if (response.isSuccessful) {
            val houses = response.body()?.map { it.asHouse() } ?: listOf()
            val previousUrl = response.headers()

            val paged = PagedHouses(houses, "", "", "", "")
            return RemoteResult.Success(paged)
        } else {
            return RemoteResult.Error("Could not fetch houses")
        }
    }
}