package io.redandroid.westerosandbeyond.local.modules.house

import androidx.paging.PagingSource
import androidx.room.withTransaction
import io.redandroid.westerosandbeyond.local.core.WesterosAndBeyondDatabase
import io.redandroid.westerosandbeyond.local.modules.house.model.asHouse
import io.redandroid.westerosandbeyond.local.modules.house.model.asHouseDbList
import io.redandroid.westerosandbeyond.local.modules.house.model.asHouseList
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import javax.inject.Inject

class HouseLocalImpl @Inject constructor(
    private val db: WesterosAndBeyondDatabase
): HouseLocal {

    override suspend fun loadHouses(): List<House> {
        val housesDb = db.houseDao.loadAll()

        return housesDb.asHouseList()
    }

    override suspend fun saveHouses(houses: List<House>) {
        val housesDb = houses.asHouseDbList()

        db.withTransaction {
            db.houseDao.insertAll(housesDb)
        }
    }

    override suspend fun loadHouse(houseUrl: String): House? {
        return db.houseDao.loadHouse(houseUrl)?.asHouse()
    }

//    override fun pagingSource(): PagingSource<Int, House> {
//        return db.houseDao.pagingSource()
//    }
}