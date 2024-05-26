package com.ferrarib.todolist.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
    id: String?,
) {
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
            value = content,
            enabled = addingNew,
            onValueChange = { newValue ->
                content = newValue
            })
    }
}