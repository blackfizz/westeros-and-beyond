package io.redandroid.westerosandbeyond.model.modules.house

data class HouseRemoteKey(
    val houseUrl: String,
    val currentPage: Int,
    val nextPage: Int?,
    val previousPage: Int?
)