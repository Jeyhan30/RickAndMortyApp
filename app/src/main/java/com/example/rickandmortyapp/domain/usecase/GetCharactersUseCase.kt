package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Character>> {
        return repository.getCharacters(page)
    }
}
