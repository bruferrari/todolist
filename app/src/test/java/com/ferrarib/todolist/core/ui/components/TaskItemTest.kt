package com.ferrarib.todolist.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ferrarib.todolist.R
import com.ferrarib.todolist.context
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskItemTest {

    private val content = "this is a content"
    private val ComposeContent
        @Composable get() = TaskItem(
            id = 1L,
            content = content,
            isComplete = false,
            onItemPressed = {},
            onDeleteItemPressed = {}
        ) {}

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `ensure task item is being displayed`() {
        composeTestRule.setContent { ComposeContent }

        composeTestRule.onNodeWithText(content).assertIsDisplayed()
    }

    @Test
    fun `ensure task item has a delete button`() {
        composeTestRule.setContent { ComposeContent }

        composeTestRule.onNodeWithContentDescription(
            context.getString(R.string.todos_list_delete_button_description)
        ).assertIsDisplayed()
    }

    @Test
    fun `ensure task item has a checkbox`() {
        composeTestRule.setContent { ComposeContent }

        composeTestRule.onNodeWithContentDescription(
            context.getString(R.string.todos_list_checkbox_description)
        ).assertIsDisplayed()
    }
}