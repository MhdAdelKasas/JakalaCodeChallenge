package com.example.jakalacodechallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PersonScreen(
    viewModel: PersonViewModel
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        // Trigger the fetchMovies() when the composable is first launched.
        delay(5000)
        viewModel.handleIntent(PersonIntent.LoadPerson)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.loading -> {
                // Display a loading indicator
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }
            state.error != null -> {
                // Display an error message
                Text(text = "Error: ${state.error}", color = Color.Red)
            }
            else -> {
                // Display the list of movies
                PersonList(people = state.people)
            }
        }
    }
}