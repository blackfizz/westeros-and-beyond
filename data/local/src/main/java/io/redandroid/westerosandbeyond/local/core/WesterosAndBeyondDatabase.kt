package io.redandroid.westerosandbeyond.local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.redandroid.westerosandbeyond.local.core.converter.StringListConverter
import io.redandroid.westerosandbeyond.local.modules.house.dao.HouseDao
import io.redandroid.westerosandbeyond.local.modules.house.dao.HouseRemoteKeyDao
import io.redandroid.westerosandbeyond.local.modules.house.model.HouseDb
import io.redandroid.westerosandbeyond.local.modules.house.model.HouseRemoteKeyDb

@Database(entities = [HouseDb::class, HouseRemoteKeyDb::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class WesterosAndBeyondDatabase: RoomDatabase() {

    abstract val houseDao: HouseDao
    abstract val remoteKeysDao: HouseRemoteKeyDao
}