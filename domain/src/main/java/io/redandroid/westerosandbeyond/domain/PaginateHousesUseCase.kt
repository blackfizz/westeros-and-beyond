package io.redandroid.westerosandbeyond.domain

import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import javax.inject.Inject

class PaginateHousesUseCase @Inject constructor(
    private val repository: HouseRepository
) {

    suspend operator fun invoke() {

    }
}