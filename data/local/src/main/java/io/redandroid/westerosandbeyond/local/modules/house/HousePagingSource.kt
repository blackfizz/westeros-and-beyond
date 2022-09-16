package io.redandroid.westerosandbeyond.local.modules.house

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.redandroid.westerosandbeyond.local.modules.house.dao.HouseDao
import io.redandroid.westerosandbeyond.local.modules.house.model.asHouseList
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import timber.log.Timber

// Inspired by https://github.com/blueglyph/PagingSampleModified/blob/initial_key/app/src/main/java/paging/android/example/com/pagingsample/CheeseDao.kt
class HousePagingSource(
    private val houseDao: HouseDao
): PagingSource<Int, House>() {

    override val jumpingSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, House>): Int? {
        val position =  state.anchorPosition ?: 0
        val key = maxOf(0, position - state.config.initialLoadSize / 2)
        return key
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {
        val pageSize = PagedHouses.pageSize
        val pageNumber = params.key ?: 0
        val count = houseDao.getAmountOfHouses()
        val data = houseDao.loadSlicedHouses(pageNumber, params.loadSize).asHouseList()

        val isRefresh = params is LoadParams.Refresh
        return LoadResult.Page(
            data = data,
            prevKey = if (pageNumber > 0) maxOf(0, pageNumber - pageSize) else null,
            nextKey = if (pageNumber + data.size < count) pageNumber + data.size else null,
            itemsBefore = if (isRefresh) pageNumber else UNDEF,
            itemsAfter = if (isRefresh) maxOf(0, count - pageNumber - data.size) else UNDEF,
        ).also {
            val first = data.firstOrNull()?.url
            val p = params::class.simpleName?.first()
            Timber.d("load($p key=${params.key}, dataSize=${data.size}) -> ${it.prevKey} / ${it.nextKey}, ${it.itemsBefore} / ${it.itemsAfter}, data=$first (${data.count()}) | db count: $count")
        }
    }

    companion object {
        val UNDEF = -2147483648
    }
}