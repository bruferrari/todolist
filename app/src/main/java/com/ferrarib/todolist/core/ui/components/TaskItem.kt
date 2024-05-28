package com.ferrarib.todolist.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ferrarib.todolist.R
import com.ferrarib.todolist.core.ui.tokens.SizeTokens

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    id: Long?,
    content: String,
    isComplete: Boolean,
    onItemPressed: (Long?) -> Unit,
    onDeleteItemPressed: (Long?) -> Unit,
    onCheckPressed: (Long?) -> Unit
) {
    val checkboxContentDescription = stringResource(id = R.string.todos_list_checkbox_description)

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
                    onItemPressed.invoke(id)
                })
    ) {
        Checkbox(
            modifier = Modifier.semantics {
                contentDescription = checkboxContentDescription
            },
            checked = isComplete,
            onCheckedChange = { onCheckPressed.invoke(id) },
            colors = CheckboxDefaults.colors()
                .copy(
                    uncheckedCheckmarkColor = MaterialTheme.colorScheme.onPrimary,
                    checkedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedBorderColor = MaterialTheme.colorScheme.onPrimary
                )
        )

        Text(
            modifier = Modifier
                .weight(1f)
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
            modifier = Modifier
                .padding(end = SizeTokens.small),
            onClick = { onDeleteItemPressed.invoke(id) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete_24),
                contentDescription = stringResource(id = R.string.todos_list_delete_button_description),
            )
        }
    }
}