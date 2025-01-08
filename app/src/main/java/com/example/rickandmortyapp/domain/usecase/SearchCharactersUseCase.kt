package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(query: String): Result<List<Character>> {
        return repository.searchCharacters(query)
    }
}