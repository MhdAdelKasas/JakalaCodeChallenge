package com.example.jakalacodechallenge.ui

import com.example.jakalacodechallenge.data.domain.Person

data class PersonViewState(
    val loading: Boolean = false,
    val count: Int = 0,
    val nextUrl: String? = null,
    val previousUrl: String? = null,
    val people: List<Person> = emptyList(),
    val error: String? = null
)
