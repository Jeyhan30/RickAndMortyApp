package com.example.rickandmortyapp.utils

object ErrorMapper {
    fun mapError(exception: Throwable): String {
        return when (exception) {
            is java.net.UnknownHostException -> "No internet connection"
            is java.net.SocketTimeoutException -> "Request timed out"
            else -> "Something went wrong: ${exception.message}"
        }
    }
}
