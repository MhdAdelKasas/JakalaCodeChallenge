package com.example.jakalacodechallenge.network

import com.example.jakalacodechallenge.data.domain.PeopleResponse
import com.example.jakalacodechallenge.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(
    private val client: HttpClient
) {

    suspend fun getPeople(): PeopleResponse =
        client.get("${Constants.BaseUrl}${Constants.getPeople}").body<PeopleResponse>()
}