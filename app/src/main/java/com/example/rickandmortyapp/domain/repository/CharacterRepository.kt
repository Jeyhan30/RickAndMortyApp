package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<List<Character>>
    suspend fun getCharacterById(id: Int): Result<Character>
    suspend fun searchCharacters(query: String): Result<List<Character>>
//    suspend fun toggleFavorite(character: Character)
//    fun getFavoriteCharacters(): Flow<List<Character>>
}
