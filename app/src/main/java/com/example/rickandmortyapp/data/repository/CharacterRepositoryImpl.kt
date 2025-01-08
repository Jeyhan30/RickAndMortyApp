package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.local.dao.CharacterDao
import com.example.rickandmortyapp.data.remote.api.RickAndMortyApi
import com.example.rickandmortyapp.data.remote.dto.CharacterDto
import com.example.rickandmortyapp.data.remote.dto.Location
import com.example.rickandmortyapp.data.remote.dto.Origin
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
    private val dao: CharacterDao
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Result<List<Character>> = try {
        val response = api.getCharacters(page)
        val characters = response.results.map { it.toDomainModel() }
        Result.success(characters)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCharacterById(id: Int): Result<Character> = try {
        val response = api.getCharacterById(id)
        Result.success(response.toDomainModel())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun searchCharacters(query: String): Result<List<Character>> = try {
        val response = api.searchCharacters(query)
        val characters = response.results.map { it.toDomainModel() }
        Result.success(characters)
    } catch (e: Exception) {
        Result.failure(e)
    }

    private fun CharacterDto.toDomainModel() = Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        origin = Origin(
            name = origin.name,
            url = origin.url
        ),
        location = Location(
            name = location.name,
            url = location.url
        ),
        image = image
    )
}