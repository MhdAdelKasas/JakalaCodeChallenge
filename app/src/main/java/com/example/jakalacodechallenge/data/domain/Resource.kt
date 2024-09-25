package com.example.jakalacodechallenge.data.domain

sealed class Resource<T>(val data: T? = null, val error: String? = null) {

    data class Success<T>(val response: T): Resource<T>(data = response)

    data class Error<T>(val message: String): Resource<T>(error = message)
}