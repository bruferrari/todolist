package com.ferrarib.todolist.presentation.todos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ferrarib.todolist.R
import com.ferrarib.todolist.ui.components.InteractiveDialog
import com.ferrarib.todolist.ui.components.ScreenTitle
import com.ferrarib.todolist.ui.tokens.SizeTokens
import timber.log.Timber

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel,
    onDetailsClicked: (String) -> Unit,
    onAddNewClicked: () -> Unit
) {
    val taskList by viewModel.tasksListState.collectAsState()
    var shouldDisplayDeletionDialog by remember { mutableStateOf(false) }

    if (shouldDisplayDeletionDialog) {
        InteractiveDialog(
            title = stringResource(id = R.string.deletion_dialog_title),
            content = stringResource(id = R.string.deletion_dialog_content),
            onDismissRequest = {
                shouldDisplayDeletionDialog = false
            },
            onConfirmation = {
                // TODO: call deletion
                shouldDisplayDeletionDialog = false
            })
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenTitle(title = stringResource(id = R.string.todos_list_screen_title))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(taskList) { index, item ->
                    val paddingTop = if (index == 0) 20.dp else 0.dp
                    val paddingBottom = if (index == taskList.size - 1) 20.dp else 0.dp
                    var isComplete by remember { mutableStateOf(false) }

                    TodoItem(
                        modifier = Modifier.padding(top = paddingTop, bottom = paddingBottom),
                        content = item,
                        isComplete = isComplete,
                        onItemClicked = { id ->
                            onDetailsClicked.invoke(id)
                        },
                        onDeleteItemClicked = { id ->
                            Timber.d("Dialog shown for id: $id")
                            shouldDisplayDeletionDialog = true
                        },
                        onComplete = { id ->
                            isComplete = !isComplete
                            Timber.d("Checked: $id")
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = SizeTokens.larger, end = SizeTokens.larger),
            onClick = onAddNewClicked
        ) {
            Icon(Icons.Filled.Add, null)
        }
    }
}

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    content: String,
    isComplete: Boolean,
    onItemClicked: (String) -> Unit,
    onDeleteItemClicked: (String) -> Unit,
    onComplete: (String) -> Unit
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
        Checkbox(
            checked = isComplete,
            onCheckedChange = { onComplete.invoke("1") },
            colors = CheckboxDefaults.colors()
                .copy(
                    uncheckedCheckmarkColor = MaterialTheme.colorScheme.onPrimary,
                    checkedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedBorderColor = MaterialTheme.colorScheme.onPrimary
                )
        )

        Text(
            modifier = Modifier
                .padding(end = SizeTokens.medium),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary.copy(
                alpha = if (isComplete) 0.6f else 1f
            ),
            textDecoration = if (isComplete) TextDecoration.LineThrough else TextDecoration.None,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = content
        )

        IconButton(
            modifier = Modifier.padding(end = 16.dp),
            onClick = { onDeleteItemClicked.invoke("1") }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete_24),
                contentDescription = null
            )
        }
    }
}