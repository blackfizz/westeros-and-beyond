package io.redandroid.westerosandbeyond.remote.modules.house.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.redandroid.westerosandbeyond.model.modules.house.House

@JsonClass(generateAdapter = true)
data class HouseDTO(
    @Json(name = "url")
    val url: String, // Url to house

    @Json(name = "name")
    val name: String,

    @Json(name = "region")
    val region: String,

    @Json(name = "coatOfArms")
    val coatOfArms: String,

    @Json(name = "words")
    val words: String,

    @Json(name = "titles")
    val titles: List<String>,

    @Json(name = "seats")
    val seats: List<String>,

    @Json(name = "currentLord")
    val currentLord: String, // Url to character

    @Json(name = "heir")
    val heir: String,   // Url to character

    @Json(name = "overlord")
    val overlord: String, // Url to house

    @Json(name = "founded")
    val founded: String,

    @Json(name = "founder")
    val founder: String, // Url to character

    @Json(name = "diedOut")
    val diedOut: String,

    @Json(name = "ancestralWeapons")
    val ancestralWeapons: List<String>,

    @Json(name = "cadetBranches")
    val cadetBranches: List<String>,

    @Json(name = "swornMembers")
    val swornMembers: List<String> // Url to character
)

fun HouseDTO.asHouse() = House(
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
