package com.ferrarib.todolist.core.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ferrarib.todolist.R
import com.ferrarib.todolist.core.ui.theme.TodolistTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreenTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `ensure title displayed is the same as set`() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val title = context.getString(
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