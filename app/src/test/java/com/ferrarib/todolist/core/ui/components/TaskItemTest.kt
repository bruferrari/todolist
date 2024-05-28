package com.ferrarib.todolist.core.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `ensure display icon for removing task`() {
        composeTestRule.setContent {
            TaskItem(
                id = 1L,
                content = "this is a content",
                isComplete = false,
                onItemPressed = {},
                onDeleteItemPressed = {}
            ) {}
        }

        composeTestRule.onNodeWithText("this is a content").assertIsDisplayed()
    }
}