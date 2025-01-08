//package com.example.rickandmortyapp.domain.usecase
//
//import com.example.rickandmortyapp.domain.model.Character
//import com.example.rickandmortyapp.domain.repository.CharacterRepository
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class GetFavoriteCharactersUseCase(
//    private val repository: CharacterRepository
//) {
//    operator fun invoke(): Flow<List<Character>> {
//        return repository.getFavoriteCharacters()
//    }
//}