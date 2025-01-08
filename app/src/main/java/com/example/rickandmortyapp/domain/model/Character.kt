package com.example.rickandmortyapp.domain.model

import com.example.rickandmortyapp.data.remote.dto.Location
import com.example.rickandmortyapp.data.remote.dto.Origin

// Character.kt
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String
)

data class Origin(
    val name: String,
    val url: String
)

data class Location(
    val name: String,
    val url: String
)