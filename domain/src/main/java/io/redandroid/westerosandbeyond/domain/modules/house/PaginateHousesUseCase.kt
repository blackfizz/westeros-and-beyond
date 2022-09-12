package io.redandroid.westerosandbeyond.domain.modules.house

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import javax.inject.Inject

class PaginateHousesUseCase @Inject constructor(
    private val repository: HouseRepository,
) {

    suspend operator fun invoke():List<House> {
        return repository.getHouses()
    }
}

        /*
        Pager<Int, House>  {
        return Pager(
            config = PagingConfig(PagedHouses.pageSize),
            remoteMediator =  HouseRemoteMediator()
        ) {

        }
         */