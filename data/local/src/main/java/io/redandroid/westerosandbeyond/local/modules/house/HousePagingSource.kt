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

class HousePagingSource(
    private val houseDao: HouseDao,
    private val remoteKeyDao: HouseRemoteKeyDao
): PagingSource<Int, House>() {

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

        Timber.d("Params: $params")
        Timber.d("page: $page")

        val houseUrls = remoteKeyDao.remoteKeysByCurrentPage(page).map { it.houseUrl }
        val data = houseDao.loadHousesByUrl(houseUrls).map { it.asHouse() }

        Timber.d("Data: $data")
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else page + 1
        )
    }

}