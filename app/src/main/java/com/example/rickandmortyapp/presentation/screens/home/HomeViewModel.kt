//package com.example.rickandmortyapp.presentation.viewmodel
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.rickandmortyapp.data.remote.ApiClient
//import kotlinx.coroutines.launch
//
//class HomeViewModel : ViewModel() {
//    private val _characters = mutableStateOf<List<Character>>(emptyList())
//    val characters: State<List<Character>> get() = _characters
//
//    private val _loading = mutableStateOf(true)
//    val loading: State<Boolean> get() = _loading
//
//    init {
//        fetchCharacters()
//    }
//
//    private fun fetchCharacters() {
//        viewModelScope.launch {
//            _loading.value = true
//            try {
//                val response = ApiClient.api.getCharacters()
//                if (response.isSuccessful) {
//                    _characters.value = response.body()?.results ?: emptyList()
//                }
//            } catch (e: Exception) {
//                // Handle Error
//            } finally {
//                _loading.value = false
//            }
//        }
//    }
//}

package com.example.rickandmortyapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.usecase.GetCharactersUseCase
import com.example.rickandmortyapp.utils.handleResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    private var currentPage = 1

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _state.value = HomeScreenState.Loading
            try {
                getCharactersUseCase(currentPage).handleResult(
                    onSuccess = { characters ->
                        _state.value = HomeScreenState.Success(characters)
                    },
                    onFailure = { error ->
                        _state.value =
                            HomeScreenState.Error(error.message ?: "Failed to fetch characters")
                    }
                )
            } catch (e: Exception) {
                _state.value = HomeScreenState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}

    sealed class HomeScreenState {
    data object Loading : HomeScreenState()
    data class Success(val characters: List<Character>) : HomeScreenState()
    data class Error(val message: String) : HomeScreenState()

}
