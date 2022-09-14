package io.redandroid.westerosandbeyond.local.modules.house

import androidx.paging.PagingSource
import androidx.room.withTransaction
import io.redandroid.westerosandbeyond.local.core.WesterosAndBeyondDatabase
import io.redandroid.westerosandbeyond.local.modules.house.model.*
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.HouseRemoteKey
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import timber.log.Timber
import javax.inject.Inject

class HouseLocalImpl @Inject constructor(
    private val db: WesterosAndBeyondDatabase
): HouseLocal {

    private var pagingSource : PagingSource<Int, House>? = null

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
        Timber.d("Delete ALl DB")
        db.withTransaction {
        }
    }

    override suspend fun insertPagedHouses(
        houses: List<House>,
        remoteKeys: List<HouseRemoteKey>,
        shouldClear: Boolean
    ) {
        db.withTransaction {
            if (shouldClear) {
                db.remoteKeysDao.deleteAll()
                db.houseDao.clearAll()
            }

            insertHouses(houses)
            insertRemoteKeys(remoteKeys)
        }

        pagingSource?.invalidate()
    }

    override suspend fun insertRemoteKeys(remoteKeys: List<HouseRemoteKey>) {
        val dbKeys = remoteKeys.map { it.asRemoteKeyDb() }
        db.remoteKeysDao.insertAll(dbKeys)
    }

    override suspend fun loadRemoteKeyByUrl(hourseUrl: String): HouseRemoteKey? {
        return db.remoteKeysDao.remoteKeyByUrl(hourseUrl)?.asRemoteKey()
    }

    override fun pagingSource(): PagingSource<Int, House> {
        pagingSource = HousePagingSource(db.houseDao, db.remoteKeysDao)
        return pagingSource!!
    }
}