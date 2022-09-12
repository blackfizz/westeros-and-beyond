package io.redandroid.westerosandbeyond.local.modules.house.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.redandroid.westerosandbeyond.model.modules.house.House

@Entity(tableName = "houses")
data class HouseDb(
    @PrimaryKey(autoGenerate = false)
    val url: String, // Url to house and also functions as id
    val name: String = "",
    val region: String = "",
    val coatOfArms: String = "",
    val words: String = "",
    val titles: List<String> = listOf(),
    val seats: List<String> = listOf(),
    val currentLord: String = "", // Url to character
    val heir: String = "",   // Url to character
    val overlord: String = "", // Url to house
    val founded: String = "",
    val founder: String = "", // Url to character
    val diedOut: String = "",
    val ancestralWeapons: List<String> = listOf(),
    val cadetBranches: List<String> = listOf(),
    val swornMembers: List<String> = listOf() // Url to character
)

fun HouseDb.asHouse() = House(
    url = url,
    name = name,
    region = region,
    coatOfArms = coatOfArms,
    words = words,
    titles = titles,
    seats = seats,
    currentLord = currentLord,
    heir = heir,
    overlord = overlord,
    founded = founded,
    founder = founder,
    diedOut = diedOut,
    ancestralWeapons = ancestralWeapons,
    cadetBranches = cadetBranches,
    swornMembers = swornMembers
)


fun House.asHouseDb() = HouseDb(
    url = url,
    name = name,
    region = region,
    coatOfArms = coatOfArms,
    words = words,
    titles = titles,
    seats = seats,
    currentLord = currentLord,
    heir = heir,
    overlord = overlord,
    founded = founded,
    founder = founder,
    diedOut = diedOut,
    ancestralWeapons = ancestralWeapons,
    cadetBranches = cadetBranches,
    swornMembers = swornMembers
)

fun List<HouseDb>.asHouseList() = map { it.asHouse() }
fun List<House>.asHouseDbList() = map { it.asHouseDb() }