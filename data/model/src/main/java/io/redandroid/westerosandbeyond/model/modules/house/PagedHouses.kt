package io.redandroid.westerosandbeyond.model.modules.house

data class PagedHouses(
    val houses: List<House>,
    val previousUrl: String,
    val nextUrl: String,
    val firstUrl: String,
    val lastUrl: String
) {
    companion object {
        const val pageSize = 10
    }
}