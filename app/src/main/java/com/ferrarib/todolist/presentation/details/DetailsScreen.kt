package com.ferrarib.todolist.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ferrarib.todolist.R
import com.ferrarib.todolist.ui.components.ScreenTitle
import com.ferrarib.todolist.ui.tokens.SizeTokens

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    id: Long?,
) {
    id?.run { viewModel.getTask(this) }

    val selectedTaskState by viewModel.selectedTaskState.collectAsState()

    var content by remember { mutableStateOf("") }
    val addingNew = id == null

    Column(modifier = modifier) {
        ScreenTitle(
            title = if (addingNew)
                stringResource(id = R.string.details_screen_new_title)
            else
                stringResource(id = R.string.details_screen_title)
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = SizeTokens.large, end = SizeTokens.large),
            value = if (addingNew) content else selectedTaskState?.content ?: "",
            enabled = addingNew,
            onValueChange = { newValue ->
                content = newValue
            })

        if (addingNew) {
            Spacer(modifier = Modifier.height(SizeTokens.large))

            Button(
                modifier = Modifier.padding(start = SizeTokens.large),
                onClick = {
                    viewModel.addTask(content)
                }) {
                Text(text = stringResource(id = R.string.details_screen_save_button_label))
            }
        }
    }
}