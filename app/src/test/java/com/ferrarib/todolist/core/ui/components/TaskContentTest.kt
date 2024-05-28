package com.ferrarib.todolist.core.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ferrarib.todolist.RobolectricActivity
import com.ferrarib.todolist.core.ui.theme.TodolistTheme
import com.ferrarib.todolist.domain.model.Task
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.ferrarib.todolist.R

@RunWith(AndroidJUnit4::class)
class TaskContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<RobolectricActivity>()

    @Test
    fun `ensure component is being displayed`() {
        composeTestRule.setContent {
            TodolistTheme {
                TaskContent(
                    task = Task(id = 1L, content = "test", isComplete = false),
                    onSaveButtonPressed = {})
            }
        }

        composeTestRule.onNodeWithText("test").assertExists()
        composeTestRule.onNodeWithText("test").assertIsDisplayed()
    }

    @Test
    fun `ensure that typed text is being displayed`() {
        val input = "test"
        val hint = "hint"

        composeTestRule.setContent {
            TaskContent(
                task = null,
                hint = hint,
                onSaveButtonPressed = {}
            )
        }

        composeTestRule.onNodeWithText(hint).performTextInput(input)
        composeTestRule.onNodeWithText(input).assertIsDisplayed()
    }

    @Test
    fun `ensure save button is being displayed when adding new task`() {
        composeTestRule.setContent {
            TaskContent(
                task = null,
                onSaveButtonPressed = {}
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.details_screen_save_button_label)
        ).assertIsDisplayed()
    }

    @Test
    fun `ensure save button is not being displayed when viewing a task details`() {
        composeTestRule.setContent {
            TaskContent(
                task = Task(id = 1L, content = "test", isComplete = false),
                onSaveButtonPressed = {}
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.details_screen_save_button_label)
        ).assertIsNotDisplayed()
    }
}