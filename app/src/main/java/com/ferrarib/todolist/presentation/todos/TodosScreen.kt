package com.ferrarib.todolist.presentation.todos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferrarib.todolist.ui.theme.Purple80
import com.ferrarib.todolist.ui.theme.TodolistTheme
import timber.log.Timber

val mockList: List<String>
    get() {
        val temp = mutableListOf<String>()
        (0..20).forEach { index ->
            temp.add("This is a test $index")
        }

        return temp.toList()
    }

@Composable
fun TodosScreen(
    modifier: Modifier = Modifier,
    onDetailsClicked: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            items(mockList) { item ->
                TodoItem(content = item, onItemClicked = { id ->
                    onDetailsClicked.invoke(id)
                })
            }
        }
    }
}

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    content: String,
    onItemClicked: (String) -> Unit
) {
    val shape = RoundedCornerShape(16.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .background(color = Purple80, shape = shape)
            .clip(shape)
            .clickable(
                enabled = true,
                onClick = {
                    onItemClicked.invoke("1")
                })
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = content
        )
    }
}