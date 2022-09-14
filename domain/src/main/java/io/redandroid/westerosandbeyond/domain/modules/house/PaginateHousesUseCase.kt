package io.redandroid.westerosandbeyond.domain.modules.house

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import javax.inject.Inject

class PaginateHousesUseCase @Inject constructor(
    private val repository: HouseRepository,
) {

    @OptIn(ExperimentalPagingApi::class)
    operator fun invoke(): Pager<Int, House> {
        return Pager(
            config = PagingConfig(
                pageSize = PagedHouses.pageSize
            ),
            remoteMediator = repository.getHouseRemoteMediator()
        ) {
            repository.getHousePagingSource()
        }
    }
}
