package io.redandroid.westerosandbeyond.repository.modules.house

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.HouseRemoteKey
import io.redandroid.westerosandbeyond.model.modules.house.createRemoteKeys
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class HouseRemoteMediator(
    private val remote: HouseRemote,
    private val local: HouseLocal
): RemoteMediator<Int, House>() {

    override suspend fun initialize(): InitializeAction {
        return if (local.getAmountOfHouses() == 0) InitializeAction.LAUNCH_INITIAL_REFRESH
        else InitializeAction.SKIP_INITIAL_REFRESH
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, House>): HouseRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { url ->
                local.loadRemoteKeyByUrl(url)
            }
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, House>): MediatorResult {

        Timber.d("LoadType: $loadType")

        val pageNumber = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.currentPage ?: 1
            }
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)

                val remoteKey = local.loadRemoteKeyByUrl(lastItem.url) ?: throw Exception("No remote key found")

                Timber.d("Append remoteKey $remoteKey")
                remoteKey.nextPage
            }
        } ?: return MediatorResult.Success(endOfPaginationReached = true)

        Timber.d("Before Fetching. PageNumber: $pageNumber")
        val response = remote.fetchPagedHouses(pageNumber)
        val shouldClear = pageNumber == 1 && loadType == LoadType.REFRESH

        Timber.d("After Fetching")
        if (response is RemoteResult.Success) {
            Timber.d("Fetch Success")
            val data = response.data
            val houses = data.houses
            val endReached = data.nextUrl.isBlank()
            val remoteKeys = houses.createRemoteKeys(pageNumber, !endReached)

            local.insertPagedHouses(houses, remoteKeys, shouldClear)

            return MediatorResult.Success(
                endOfPaginationReached = endReached
            )
        } else {
            Timber.d("Fetch Failed")
            return MediatorResult.Error(IOException("Remote response was not successful"))
        }
    }
}