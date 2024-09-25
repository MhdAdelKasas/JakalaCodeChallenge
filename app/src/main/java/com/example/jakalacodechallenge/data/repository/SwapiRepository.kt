package com.example.jakalacodechallenge.data.repository

import com.example.jakalacodechallenge.data.domain.PeopleResponse
import com.example.jakalacodechallenge.data.domain.Person
import com.example.jakalacodechallenge.network.ApiService
import kotlinx.coroutines.delay

class SwapiRepository(
    private val api: ApiService
) {

    suspend fun getPeople(): PeopleResponse {
        // Simulate fetching data from https://swapi.dev/api/people/1/
        delay(5000)
        return api.getPeople()
    }
}