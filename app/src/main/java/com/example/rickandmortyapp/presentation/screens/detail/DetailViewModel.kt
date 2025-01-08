package com.example.rickandmortyapp.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.usecase.GetCharacterDetailUseCase
import com.example.rickandmortyapp.utils.ErrorMapper
import com.example.rickandmortyapp.utils.handleResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle["characterId"])

    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    init {
        loadCharacterDetail()
    }

    private fun loadCharacterDetail() {
        viewModelScope.launch {
            _state.value = DetailScreenState.Loading
            try {
                getCharacterDetailUseCase(characterId).handleResult(
                    onSuccess = { character ->
                        _state.value = DetailScreenState.Success(character)
                    },
                    onFailure = { error ->
                        _state.value = DetailScreenState.Error(ErrorMapper.mapError(error))
                    }
                )
            } catch (e: Exception) {
                _state.value = DetailScreenState.Error(ErrorMapper.mapError(e))
            }
        }
    }
}

sealed class DetailScreenState {
    data object Loading : DetailScreenState()
    data class Success(val character: Character) : DetailScreenState()
    data class Error(val message: String) : DetailScreenState()
}