package io.redandroid.westerosandbeyond.local.modules.house

import androidx.paging.PagingSource
import androidx.room.withTransaction
import io.redandroid.westerosandbeyond.local.core.WesterosAndBeyondDatabase
import io.redandroid.westerosandbeyond.local.modules.house.model.*
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.HouseRemoteKey
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import javax.inject.Inject

class HouseLocalImpl @Inject constructor(
    private val db: WesterosAndBeyondDatabase
): HouseLocal {

    override suspend fun loadHouses(): List<House> {
        val housesDb = db.houseDao.loadAllHouses()

        return housesDb.asHouseList()
    }

    override suspend fun insertHouses(houses: List<House>) {
        val housesDb = houses.asHouseDbList()

        db.houseDao.insertAll(housesDb)
    }

    override suspend fun loadHouse(houseUrl: String): House? {
        return db.houseDao.loadHouseByUrl(houseUrl)?.asHouse()
    }

    override suspend fun getAmountOfHouses(): Int {
        return db.houseDao.getAmountOfHouses()
    }

    override suspend fun deleteAll() {
        db.withTransaction {
            db.remoteKeysDao.deleteAll()
            db.houseDao.clearAll()
        }
    }

    override suspend fun insertRemoteKeys(remoteKeys: List<HouseRemoteKey>) {
        val dbKeys = remoteKeys.map { it.asRemoteKeyDb() }
        db.remoteKeysDao.insertAll(dbKeys)
    }

    override suspend fun loadRemoteKeyByUrl(hourseUrl: String): HouseRemoteKey? {
        return db.remoteKeysDao.remoteKeyByUrl(hourseUrl)?.asRemoteKey()
    }

    override fun pagingSource(): PagingSource<Int, House> {
        return HousePagingSource(db.houseDao, db.remoteKeysDao)
    }
}