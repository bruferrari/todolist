package com.ferrarib.todolist.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ferrarib.todolist.R
import com.ferrarib.todolist.core.ui.components.ScreenTitle
import com.ferrarib.todolist.core.ui.components.TaskContent
import com.ferrarib.todolist.core.ui.tokens.SizeTokens

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    id: Long?,
    onSaveButtonPressed: () -> Unit
) {
    id?.run { viewModel.getTask(this) }

    val selectedTaskState by viewModel.selectedTaskState.collectAsState()

    val addingNew = id == null

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        ScreenTitle(
            title = if (addingNew)
                stringResource(id = R.string.details_screen_new_title)
            else
                stringResource(id = R.string.details_screen_title)
        )

        TaskContent(
            modifier = Modifier.padding(
                start = SizeTokens.large,
                end = SizeTokens.large,
                bottom = if (addingNew) SizeTokens.zero else SizeTokens.larger
            ),
            task = selectedTaskState,
            onSaveButtonPressed = { content ->
                viewModel.addNewTask(content)
                onSaveButtonPressed.invoke()
            }
        )
    }
}