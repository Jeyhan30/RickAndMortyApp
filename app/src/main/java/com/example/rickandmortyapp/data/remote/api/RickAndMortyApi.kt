package com.example.rickandmortyapp.data.remote.api

import com.example.rickandmortyapp.data.remote.dto.CharacterResponse
import com.example.rickandmortyapp.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto

    @GET("character")
    suspend fun searchCharacters(
        @Query("name") name: String
    ): CharacterResponse
}