package com.ferrarib.todolist.presentation.details

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, id: String?) {
    Row(modifier = modifier) {
        Text(text = "Here goes the detail screen id: $id")
    }
}