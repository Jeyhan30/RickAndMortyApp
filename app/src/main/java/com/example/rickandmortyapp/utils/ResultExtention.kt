package com.example.rickandmortyapp.utils

inline fun <T> Result<T>.handleResult(
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    this.fold(
        onSuccess = { onSuccess(it) },
        onFailure = { onFailure(it) }
    )
}
