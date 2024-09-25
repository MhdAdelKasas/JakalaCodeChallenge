package com.example.jakalacodechallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.jakalacodechallenge.ui.components.BodyText
import com.example.jakalacodechallenge.ui.components.HeaderText

@Composable
fun ExpandableSpace(
    modifier: Modifier = Modifier,
    headerText: String,
    items: List<String>,
    scale: Float
) {
    var spaceExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            ExpandableSpaceHeader(
                headerText = headerText,
                spaceExpanded = spaceExpanded,
                onToggle = { spaceExpanded = !it }
            )
            ExpandableSpaceContent(
                spaceExpanded = spaceExpanded,
                items = items
            )
        }
    }
}

@Composable
private fun ExpandableSpaceHeader(
    headerText: String,
    spaceExpanded: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderText(
            header = headerText
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { onToggle(spaceExpanded) }
        ) {
            Icon(
                imageVector = if (spaceExpanded) {
                    Icons.Filled.KeyboardArrowUp
                } else {
                    Icons.Filled.KeyboardArrowDown
                },
                contentDescription = if (spaceExpanded) "Collapse" else "Expand"
            )
        }
    }
}

@Composable
private fun ExpandableSpaceContent(
    spaceExpanded: Boolean,
    items: List<String>
) {
    AnimatedVisibility(
        visible = spaceExpanded,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column {
            for (item in items) {
                BodyText(
                    text = item,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}