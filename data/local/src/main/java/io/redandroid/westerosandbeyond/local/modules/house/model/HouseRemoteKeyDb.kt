package io.redandroid.westerosandbeyond.local.modules.house.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.redandroid.westerosandbeyond.model.modules.house.HouseRemoteKey

@Entity(tableName = "house_remote_keys")
data class HouseRemoteKeyDb(
    @PrimaryKey
    val houseUrl: String,
    val currentPage: Int,
    val nextPage: Int?,
    val previousPage: Int?
)

fun HouseRemoteKeyDb.asRemoteKey() = HouseRemoteKey(
    houseUrl = houseUrl,
    currentPage = currentPage,
    nextPage = nextPage,
    previousPage = previousPage
)

fun HouseRemoteKey.asRemoteKeyDb() = HouseRemoteKeyDb(
    houseUrl = houseUrl,
    currentPage = currentPage,
    nextPage = nextPage,
    previousPage = previousPage
)