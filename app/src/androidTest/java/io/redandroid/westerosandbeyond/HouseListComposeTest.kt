package io.redandroid.westerosandbeyond

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import io.redandroid.westerosandbeyond.core_ui.theme.WesterosAndBeyondTheme
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailError
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailSuccess
import org.junit.Rule
import org.junit.Test


// Needs to be run in an Android 12 Emulator or lower. With Android 13 it crashes with the following error:
// https://issuetracker.google.com/issues/240993946
class HouseDetailComposeTest {

    private val house = mockedHouse()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHouseDetailSuccessStateAndShowsHouseInformation () {
        composeTestRule.setContent {
            WesterosAndBeyondTheme {
                HouseDetailSuccess(house)
            }
        }

        composeTestRule.onNodeWithText(house.name, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText(house.seats.joinToString("\n"), useUnmergedTree = true).assertIsDisplayed()
//        composeTestRule.onNodeWithText(house.words, useUnmergedTree = true).assertIsNotDisplayed()
    }

    @Test
    fun testHouseDetailErrorState () {
        composeTestRule.setContent {
            WesterosAndBeyondTheme {
                HouseDetailError()
            }
        }

        composeTestRule.onNodeWithText("House could not be found", useUnmergedTree = true).assertIsDisplayed()
    }
}