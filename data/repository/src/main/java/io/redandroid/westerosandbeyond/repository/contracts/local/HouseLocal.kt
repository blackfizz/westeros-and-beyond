package io.redandroid.westerosandbeyond.repository.contracts.local

import io.redandroid.westerosandbeyond.model.modules.house.House

interface HouseLocal {
    suspend fun loadHouses(): List<House>
    suspend fun saveHouses(houses: List<House>)
    suspend fun loadHouse(houseUrl: String): House?
}