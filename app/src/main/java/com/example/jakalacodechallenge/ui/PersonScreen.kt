package com.example.jakalacodechallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.jakalacodechallenge.data.domain.Person
import com.example.jakalacodechallenge.ui.components.BodyText
import com.example.jakalacodechallenge.ui.components.HeaderText
import com.example.jakalacodechallenge.ui.components.TitleText
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