package io.redandroid.westerosandbeyond.repository.contracts.local

import androidx.paging.PagingSource
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.HouseRemoteKey

interface HouseLocal {
    suspend fun loadHouses(): List<House>
    suspend fun insertHouses(houses: List<House>)
    suspend fun loadHouse(houseUrl: String): House?
    suspend fun getAmountOfHouses(): Int
    suspend fun deleteAll()

    suspend fun insertRemoteKeys(remoteKeys: List<HouseRemoteKey>)
    suspend fun loadRemoteKeyByUrl(hourseUrl: String): HouseRemoteKey?

    fun pagingSource(): PagingSource<Int, House>
}