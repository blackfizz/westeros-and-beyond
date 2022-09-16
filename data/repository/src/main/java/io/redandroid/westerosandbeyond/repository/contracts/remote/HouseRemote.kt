package io.redandroid.westerosandbeyond.repository.contracts.remote

import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses

interface HouseRemote {
    suspend fun fetchPagedHouses(pageNumber: Int): RemoteResult<PagedHouses>
}