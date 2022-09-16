package io.redandroid.westerosandbeyond.local.modules.house.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.redandroid.westerosandbeyond.local.modules.house.model.HouseRemoteKeyDb

@Dao
interface HouseRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<HouseRemoteKeyDb>)

    @Query("SELECT * FROM house_remote_keys WHERE houseUrl = :url")
    suspend fun remoteKeyByUrl(url: String): HouseRemoteKeyDb?

    @Query("DELETE FROM house_remote_keys")
    suspend fun deleteAll()
}