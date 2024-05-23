package com.ferrarib.todolist.presentation.todos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ferrarib.todolist.R
import com.ferrarib.todolist.ui.components.ScreenTitle

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
        ScreenTitle(title = "Your TO-DOs")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(mockList) { index, item ->
                val paddingTop = if (index == 0) 20.dp else 0.dp
                val paddingBottom = if (index == mockList.size - 1) 20.dp else 0.dp

                TodoItem(
                    modifier = Modifier.padding(top = paddingTop, bottom = paddingBottom),
                    content = item,
                    onItemClicked = { id ->
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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                enabled = true,
                onClick = {
                    onItemClicked.invoke("1")
                })
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            text = content
        )

        IconButton(
            modifier = Modifier.padding(end = 16.dp),
            onClick = { /*TODO*/ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete_24),
                contentDescription = null
            )
        }
    }
}