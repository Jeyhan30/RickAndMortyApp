package com.example.rickandmortyapp.presentation.screens.search
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.usecase.SearchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SearchScreenState>(SearchScreenState.Initial) // Changed initial state
    val state: StateFlow<SearchScreenState> = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        _searchQuery
            .debounce(300)
            .filter { it.length >= 2 }
            .onEach { query ->
                viewModelScope.launch { performSearch(query) }
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            _state.value = SearchScreenState.Initial // Reset to initial state when query is empty
        }
    }

    private suspend fun performSearch(query: String) {
        _state.value = SearchScreenState.Loading
        try {
            searchCharactersUseCase(query).fold(
                onSuccess = { characters ->
                    _state.value = if (characters.isEmpty()) {
                        SearchScreenState.Empty
                    } else {
                        SearchScreenState.Success(characters)
                    }
                },
                onFailure = { error ->
                    _state.value = SearchScreenState.Error(error.message ?: "Unknown error occurred")
                }
            )
        } catch (e: Exception) {
            _state.value = SearchScreenState.Error(e.message ?: "Unknown error occurred")
        }
    }
}

sealed class SearchScreenState {
    data object Initial : SearchScreenState() // Added initial state
    data object Loading : SearchScreenState()
    data object Empty : SearchScreenState()
    data class Success(val characters: List<Character>) : SearchScreenState()
    data class Error(val message: String) : SearchScreenState()
}