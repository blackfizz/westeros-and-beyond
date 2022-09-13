package io.redandroid.westerosandbeyond.model.modules.house

data class PagedHouses(
    val houses: List<House>,
    var previousUrl: String,
    var nextUrl: String,
    var firstUrl: String,
    var lastUrl: String
) {
    companion object {
        const val pageSize = 20
    }
}