package io.redandroid.westerosandbeyond.domain.contracts

import io.redandroid.westerosandbeyond.model.modules.house.House

interface HouseRepository {
    suspend fun getHouses(): List<House>
    suspend fun getHouse(houseUrl: String): House?
}