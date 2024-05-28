package com.ferrarib.todolist.core.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ferrarib.todolist.RobolectricActivity
import com.ferrarib.todolist.core.ui.theme.TodolistTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.ferrarib.todolist.R

@RunWith(AndroidJUnit4::class)
class InteractiveDialogTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<RobolectricActivity>()

    @Test
    fun `ensure the title displayed is the correct one`() {
        val context = composeTestRule.activity

        composeTestRule.setContent {
            TodolistTheme {
                InteractiveDialog(
                    title = context.getString(R.string.deletion_dialog_title),
                    content = context.getString(R.string.deletion_dialog_content),
                    onDismissRequest = { },
                    onConfirmation = { }
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.deletion_dialog_title))
            .assertIsDisplayed()
    }

    @Test
    fun `ensure the content displayed is the correct one`() {
        val context = composeTestRule.activity

        composeTestRule.setContent {
            TodolistTheme {
                InteractiveDialog(
                    title = context.getString(R.string.deletion_dialog_title),
                    content = context.getString(R.string.deletion_dialog_content),
                    onDismissRequest = { },
                    onConfirmation = { }
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.deletion_dialog_content))
            .assertIsDisplayed()
    }

    @Test
    fun `ensure interactive buttons are displayed`() {
        val context = composeTestRule.activity

        composeTestRule.setContent {
            TodolistTheme {
                InteractiveDialog(
                    title = context.getString(R.string.deletion_dialog_title),
                    content = context.getString(R.string.deletion_dialog_content),
                    onDismissRequest = { },
                    onConfirmation = { }
                )
            }
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.deletion_dialog_user_input_confirmation
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.deletion_dialog_user_input_dismiss
            )
        ).assertIsDisplayed()
    }
}