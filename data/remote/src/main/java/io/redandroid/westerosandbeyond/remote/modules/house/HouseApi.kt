package io.redandroid.westerosandbeyond.remote.modules.house

import io.redandroid.westerosandbeyond.remote.core.Endpoints
import io.redandroid.westerosandbeyond.remote.modules.house.model.HouseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HouseApi {

    @GET(Endpoints.pathHouses)
    @Headers("Accept: application/vnd.anapioficeandfire+json; version=1")
    suspend fun fetchHouses(
        @Query(Endpoints.queryPageNumber) pageNumber: Int
    ): Response<List<HouseDTO>>

}