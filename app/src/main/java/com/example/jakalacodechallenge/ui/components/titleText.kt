package com.example.jakalacodechallenge.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = "name: $title",
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
    )
}