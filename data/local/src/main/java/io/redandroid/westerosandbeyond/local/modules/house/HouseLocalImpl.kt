package io.redandroid.westerosandbeyond.local.modules.house

import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import javax.inject.Inject

class HouseLocalImpl @Inject constructor(

): HouseLocal {

    private val houses = listOf<House>()

    override suspend fun loadHouses(): List<House> {
        return houses
    }

    override suspend fun saveHouses(houses: List<House>) {
        this.houses + houses
    }

    override suspend fun loadHouse(houseUrl: String): House? {
        return houses.find { it.url == houseUrl }
    }
}