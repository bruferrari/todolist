package com.ferrarib.todolist.core.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ferrarib.todolist.R
import com.ferrarib.todolist.core.ui.tokens.SizeTokens
import com.ferrarib.todolist.domain.model.Task

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    task: Task?,
    onSaveButtonPressed: (String) -> Unit
) {
    var content by rememberSaveable { mutableStateOf("") }
    val addingNew = task == null

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = if (addingNew) content else task?.content ?: "",
        enabled = addingNew,
        onValueChange = { newValue ->
            content = newValue
        })

    if (addingNew) {
        Spacer(modifier = Modifier.height(SizeTokens.large))

        Button(
            modifier = Modifier.padding(start = SizeTokens.large, bottom = SizeTokens.larger),
            onClick = { onSaveButtonPressed(content) }
        ) {
            Text(text = stringResource(id = R.string.details_screen_save_button_label))
        }
    }
}