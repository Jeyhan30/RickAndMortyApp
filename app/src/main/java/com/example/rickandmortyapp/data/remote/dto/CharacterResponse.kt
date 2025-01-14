package com.example.rickandmortyapp.data.remote.dto

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDto>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)