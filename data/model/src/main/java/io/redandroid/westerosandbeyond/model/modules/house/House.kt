package io.redandroid.westerosandbeyond.model.modules.house

data class House (
    val url: String, // Url to house and also functions as id
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String>,
    val seats: List<String>,
    val currentLord: String, // Url to character
    val heir: String,   // Url to character
    val overlord: String, // Url to house
    val founded: String,
    val founder: String, // Url to character
    val diedOut: String,
    val ancestralWeapons: List<String>,
    val cadetBranches: List<String>,
    val swornMembers: List<String>, // Url to character
)

fun List<House>.createRemoteKeys(currentPage: Int, hasNext: Boolean): List<HouseRemoteKey> = map {
    HouseRemoteKey(
        houseUrl = it.url,
        currentPage = currentPage,
        nextPage = if (hasNext) currentPage + 1 else null,
        previousPage = if (currentPage == 1) null else currentPage - 1
    )
}