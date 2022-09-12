package io.redandroid.westerosandbeyond.domain.modules.house

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.redandroid.westerosandbeyond.model.modules.house.House

@OptIn(ExperimentalPagingApi::class)
class HouseRemoteMediator(): RemoteMediator<Int, House>() {

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, House>): MediatorResult {
        TODO("Not yet implemented")
    }
}