package com.ferrarib.todolist.presentation.todos

import androidx.compose.foundation.layout.Row
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodolistTheme {
        Greeting("Android")
    }
}