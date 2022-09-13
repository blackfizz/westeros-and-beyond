package io.redandroid.westerosandbeyond.repository.modules.house

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
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


            if (pagedHouses.houses.isNotEmpty()) {
                local.insertHouses(pagedHouses.houses)
            }

            local.loadHouses()
        } else {

            listOf()
        }
    }

    override suspend fun getHouse(houseUrl: String): House? = withContext(dispatcher.io) {
        local.loadHouse(houseUrl)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getHouseRemoteMediator(): RemoteMediator<Int, House> = HouseRemoteMediator(remote, local)
    override fun getHousePagingSource(): PagingSource<Int, House> = local.pagingSource()
}