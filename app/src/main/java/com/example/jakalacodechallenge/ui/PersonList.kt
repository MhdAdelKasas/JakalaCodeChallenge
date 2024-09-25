package com.example.jakalacodechallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.jakalacodechallenge.data.domain.Person

@Composable
fun PersonList(people: List<Person>) {
    var peopleVisibilities by remember {
        mutableStateOf(people.map { false })
    }

    LaunchedEffect(people) {
        peopleVisibilities = people.map { true }
    }

    LazyColumn {
        itemsIndexed(people) { index, person ->
            AnimatedVisibility(
                visible = peopleVisibilities[index],
                enter = slideInVertically() + expandVertically() + fadeIn(),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                val scale by animateFloatAsState(
                    targetValue = if (peopleVisibilities[index]) 1f else 0.5f, label = ""
                )

                PersonCard(
                    person = person,
                    scale = scale
                )
            }
        }
    }
}