package com.example.jakalacodechallenge.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    header: String
) {
    Text(
        text = header,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}