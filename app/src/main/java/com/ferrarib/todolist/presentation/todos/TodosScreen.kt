package com.ferrarib.todolist.presentation.todos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ferrarib.todolist.ui.theme.TodolistTheme

@Composable
fun TodosScreen(
    modifier: Modifier = Modifier,
    onDetailsClicked: (String) -> Unit
) {
    Row(modifier = modifier) {
        Button(onClick = {
            onDetailsClicked.invoke("1")
        }) {
            Text(text = "Navigate next")
        }
    }
}

@Composable
fun TodoItem(modifier: Modifier = Modifier, content: String) {
    Row(
        modifier = modifier
            .clickable(enabled = true, onClick = {
                // TODO: impl
            })
    ) {
        Text(text = content)
    }
}