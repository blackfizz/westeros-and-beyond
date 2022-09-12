package io.redandroid.westerosandbeyond.local.modules.house

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
    suspend fun loadAll(): List<HouseDb>

    @Query("SELECT * FROM houses WHERE url LIKE :url")
    suspend fun loadHouse(url: String): HouseDb?
//
//    @Query("SELECT * FROM houses WHERE label LIKE :query")
//    fun pagingSource(query: String): PagingSource<Int, HouseDb>

    @Query("DELETE FROM houses")
    suspend fun clearAll()
}