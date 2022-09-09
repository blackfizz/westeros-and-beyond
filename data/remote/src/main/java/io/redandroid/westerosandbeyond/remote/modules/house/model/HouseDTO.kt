package io.redandroid.westerosandbeyond.remote.modules.house.model

import io.redandroid.westerosandbeyond.model.House

data class HouseDTO(
    val url: String, // Url to house
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
    val swornMembers: List<String> // Url to character
)

fun HouseDTO.asHouse() = House(
    internalId = 0L,
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

fun List<HouseDTO>.asHouseList() = map { it.asHouse() }
