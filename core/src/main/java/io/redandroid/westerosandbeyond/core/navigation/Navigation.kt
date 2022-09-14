package io.redandroid.westerosandbeyond.core.navigation

object Navigation {
    const val houseUrlParam = "houseUrl"

    const val houseListPath = "houses"
    const val houseDetailPath = "$houseListPath/{$houseUrlParam}"

    fun generateHouseDetailPath(houseUrl: String): String {
        return "$houseListPath/$houseUrl"
    }
}