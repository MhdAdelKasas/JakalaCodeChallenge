package com.example.jakalacodechallenge.koin

import android.util.Log
import com.example.jakalacodechallenge.data.repository.SwapiRepository
import com.example.jakalacodechallenge.network.ApiService
import com.example.jakalacodechallenge.ui.PersonViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { SwapiRepository(get()) }
}
val networkModule = module {
    factory { ApiService(get()) }

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    },
                    contentType = ContentType.Any
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}

val uiModule = module {
    viewModel { PersonViewModel(get()) }
}

val appModule = module {
    includes(
        dataModule,
        networkModule,
        uiModule
    )
}