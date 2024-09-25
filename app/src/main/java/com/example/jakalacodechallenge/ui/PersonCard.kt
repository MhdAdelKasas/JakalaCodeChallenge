package com.example.jakalacodechallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.jakalacodechallenge.data.domain.Person
import com.example.jakalacodechallenge.ui.components.BodyText
import com.example.jakalacodechallenge.ui.components.HeaderText
import com.example.jakalacodechallenge.ui.components.TitleText

@Composable
fun PersonCard(
    person: Person,
    scale: Float
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            PersonCardHeader(
                person = person,
                expanded = expanded,
                onToggle = {
                    expanded = !it
                },
                scale = scale
            )
            PersonCardContent(
                expanded = expanded,
                person = person,
                scale = scale
            )
        }

    }
}

@Composable
fun PersonCardHeader(
    modifier: Modifier = Modifier,
    person: Person,
    expanded: Boolean,
    onToggle: (Boolean) -> Unit,
    scale: Float
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleText(
            title = person.name,
            modifier = Modifier
                .padding(16.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { onToggle(expanded) }
        ) {
            Icon(
                imageVector = if (expanded) {
                    Icons.Filled.KeyboardArrowUp
                } else {
                    Icons.Filled.KeyboardArrowDown
                },
                contentDescription = if (expanded) "Collapse" else "Expand"
            )
        }
    }
}

@Composable
fun PersonCardContent(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    person: Person,
    scale: Float
) {
    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            HeaderText(header = "General Personal Info")
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BodyText(
                    text = "birth year: ${person.birthYear}",
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                BodyText(
                    text = "gender: ${person.gender}",
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BodyText(
                    text = "height: ${person.height}",
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                BodyText(
                    text = "mass: ${person.mass}",
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            SmallDivider(modifier = Modifier.padding(vertical = 8.dp))
            HeaderText(header = "General Color Info")
            BodyText(
                text = "hair color: ${person.hairColor}",
                modifier = Modifier.padding(start = 4.dp)
            )
            BodyText(
                text = "skin color: ${person.skinColor}",
                modifier = Modifier.padding(start = 4.dp)
            )
            BodyText(
                text = "eye color: ${person.eyeColor}",
                modifier = Modifier.padding(start = 4.dp)
            )
            SmallDivider(modifier = Modifier.padding(vertical = 8.dp))
            HeaderText(header = "Home World")
            BodyText(
                text = person.homeWorld,
                modifier = Modifier.padding(start = 4.dp)
            )
            SmallDivider(modifier = Modifier.padding(vertical = 8.dp))
            // If I had more time I would have loaded the films data
            // from backend and displayed the details for each film
            ExpandableSpace(
                headerText = "Films",
                items = person.films,
                scale = scale
            )
            SmallDivider(modifier = Modifier.padding(vertical = 8.dp))
            // If I had more time I would have loaded the species data
            // from backend and displayed the details for each film
            ExpandableSpace(
                headerText = "Species",
                items = person.species,
                scale = scale
            )
            SmallDivider(modifier = Modifier.padding(vertical = 8.dp))
            // If I had more time I would have loaded the vehicles data
            // from backend and displayed the details for each film
            ExpandableSpace(
                headerText = "Vehicles",
                items = person.vehicles,
                scale = scale
            )
            SmallDivider(modifier = Modifier.padding(vertical = 8.dp))
            // If I had more time I would have loaded the starships data
            // from backend and displayed the details for each film
            ExpandableSpace(
                headerText = "Starships",
                items = person.starships,
                scale = scale
            )
        }
    }
}