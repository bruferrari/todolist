package com.ferrarib.todolist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ferrarib.todolist.ui.tokens.SizeTokens

@Composable
fun ScreenTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(
            horizontal = SizeTokens.large,
            vertical = SizeTokens.medium)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
    }
}