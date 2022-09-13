package io.redandroid.westerosandbeyond.remote.modules.house

import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import io.redandroid.westerosandbeyond.remote.core.converter.LinkHeaderConverter
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
            val paged = PagedHouses(houses, "", "", "", "")
            val linkHeaders = response.headers()["link"]

            if (!linkHeaders.isNullOrBlank()) {
                val links = LinkHeaderConverter().convert(linkHeaders)

                paged.nextUrl = links["next"] ?: ""
                paged.previousUrl = links["prev"] ?: ""
                paged.lastUrl = links["last"] ?: ""
                paged.firstUrl = links["first"] ?: ""
            }

            return RemoteResult.Success(paged)
        } else {
            return RemoteResult.Error("Could not fetch houses")
        }
    }
}