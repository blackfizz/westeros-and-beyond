package io.redandroid.westerosandbeyond.domain.contracts

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import io.redandroid.westerosandbeyond.model.modules.house.House

interface HouseRepository {
    suspend fun getHouses(): List<House>
    suspend fun getHouse(houseUrl: String): House?

    @OptIn(ExperimentalPagingApi::class)
    fun getHouseRemoteMediator(): RemoteMediator<Int, House>
    fun getHousePagingSource(): PagingSource<Int, House>
}