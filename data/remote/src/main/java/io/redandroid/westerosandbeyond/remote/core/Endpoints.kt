package io.redandroid.westerosandbeyond.remote.core

import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses

object Endpoints {
    const val baseUrl = "https://www.anapioficeandfire.com/api/"

    const val queryPageNumber = "pageNumber"

    const val pathHouses = "houses?pageSize=${PagedHouses.pageSize}"
}