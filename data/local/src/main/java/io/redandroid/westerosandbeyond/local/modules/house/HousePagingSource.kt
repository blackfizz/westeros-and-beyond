package io.redandroid.westerosandbeyond.local.modules.house

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.redandroid.westerosandbeyond.local.modules.house.dao.HouseDao
import io.redandroid.westerosandbeyond.local.modules.house.dao.HouseRemoteKeyDao
import io.redandroid.westerosandbeyond.local.modules.house.model.asHouse
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import timber.log.Timber
import java.lang.Integer.max
import java.lang.Integer.min

// Inspired by https://github.com/blueglyph/PagingSampleModified/blob/initial_key/app/src/main/java/paging/android/example/com/pagingsample/CheeseDao.kt
class HousePagingSource(
    private val houseDao: HouseDao,
    private val remoteKeyDao: HouseRemoteKeyDao
): PagingSource<Int, House>() {

    override val jumpingSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, House>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {
        val page = params.key ?: 1
        val pageSize = PagedHouses.pageSize

        val houseUrls = remoteKeyDao.remoteKeysByCurrentPage(page).map { it.houseUrl }
        val data = houseDao.loadHousesByUrl(houseUrls).map { it.asHouse() }

        if (page == 1 && data.isEmpty()) {
            Timber.d("Data is empty")
            return LoadResult.Error(Exception("Empty"))
        }

        val isRefresh = params is LoadParams.Refresh

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (data.isEmpty()) null else page + 1,
            itemsBefore = if (isRefresh) { if (page == 1) 0 else (page - 1) * pageSize } else UNDEF,
            itemsAfter = if (isRefresh) maxOf(0, data.size) else UNDEF,
        ).also {
            val first = data.firstOrNull()?.url
            val p = params::class.simpleName?.first()
            Timber.d("load($p key=${params.key}, loadSize=${params.loadSize}) -> ${it.prevKey} / ${it.nextKey}, ${it.itemsBefore} / ${it.itemsAfter}, data=$first (${data.count()})")
        }
    }

    companion object {
        val UNDEF = -2147483648
    }
}