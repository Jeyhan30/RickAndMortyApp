package com.example.rickandmortyapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val image: String,
    val isFavorite: Boolean = false
)