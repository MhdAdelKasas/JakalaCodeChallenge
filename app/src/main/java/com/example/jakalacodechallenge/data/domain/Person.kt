package com.example.jakalacodechallenge.data.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    @SerialName("name")
    val name: String,
    @SerialName("height")
    val height: String,
    @SerialName("mass")
    val mass: String,
    @SerialName("hair_color")
    val hairColor: String,
    @SerialName("skin_color")
    val skinColor: String,
    @SerialName("eye_color")
    val eyeColor: String,
    @SerialName("birth_year")
    val birthYear: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("homeworld")
    val homeWorld: String,
    @SerialName("films")
    val films: List<String>,
    @SerialName("species")
    val species: List<String>,
    @SerialName("vehicles")
    val vehicles: List<String>,
    @SerialName("starships")
    val starships: List<String>,
    @SerialName("created")
    val created: String,
    @SerialName("edited")
    val edited: String,
    @SerialName("url")
    val url: String,
)