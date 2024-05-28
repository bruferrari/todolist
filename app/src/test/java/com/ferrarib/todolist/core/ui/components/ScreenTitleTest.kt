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
class ScreenTitleTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<RobolectricActivity>()

    @Test
    fun `ensure title displayed is the same as set`() {
        val title = composeTestRule.activity.getString(
            R.string.details_screen_title
        )

        composeTestRule.setContent {
            TodolistTheme {
                ScreenTitle(title = title)
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
}