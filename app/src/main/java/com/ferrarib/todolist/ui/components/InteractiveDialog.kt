package com.ferrarib.todolist.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun InteractiveDialog(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    @DrawableRes iconRes: Int? = null
) {
    AlertDialog(
        modifier = modifier,
        title = {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
        },
        text = {
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
        },
        icon = iconFor(iconRes),
        onDismissRequest = onDismissRequest,
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text(text = "Yes")
            }
        }
    )
}

private fun iconFor(iconRes: Int?): @Composable (() -> Unit)? {
    if (iconRes == null) return null

    return {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}