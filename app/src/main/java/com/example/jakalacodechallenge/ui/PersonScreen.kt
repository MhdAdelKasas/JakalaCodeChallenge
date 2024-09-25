package com.example.jakalacodechallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jakalacodechallenge.data.domain.Person
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
                PersonList(persons = state.people)
            }
        }
    }
}

@Composable
fun PersonList(persons: List<Person>) {
    LazyColumn {
        items(persons) { person ->
            // Display a movie item
            PersonCard(person)
        }
    }
}

@Composable
fun PersonCard(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    ) {
        Text(text = "name: ${person.name}")
    }
}