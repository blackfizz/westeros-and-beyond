package io.redandroid.westerosandbeyond.local.modules.house.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.redandroid.westerosandbeyond.local.modules.house.model.HouseDb

@Dao
interface HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<HouseDb>)

    @Query("SELECT * FROM houses")
    suspend fun loadAllHouses(): List<HouseDb>

    @Query("SELECT * FROM houses WHERE url LIKE :url")
    suspend fun loadHouseByUrl(url: String): HouseDb?

    @Query("SELECT * FROM houses WHERE url IN (:url) ORDER BY name")
    suspend fun loadHousesByUrl(url: List<String>): List<HouseDb>

    @Query("SELECT COUNT(url) FROM houses")
    suspend fun getAmountOfHouses(): Int

    @Query("DELETE FROM houses")
    suspend fun clearAll()
}