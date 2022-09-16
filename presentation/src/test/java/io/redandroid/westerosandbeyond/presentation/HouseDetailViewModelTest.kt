package io.redandroid.westerosandbeyond.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import io.redandroid.westerosandbeyond.core.disptacher.TestDispatcherProvider
import io.redandroid.westerosandbeyond.core.navigation.Navigation
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailState
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HouseDetailViewModelTest {

    private lateinit var repository: HouseRepository
    private lateinit var savedState: SavedStateHandle

    private val houseUrl = "https://www.anapioficeandfire.co/api/houses/10"
    private val house = mockedHouse()

    @Before
    fun setup() {
        repository = mockk()
        savedState = mockk()
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `Load house successfully and receive success state`() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)

        coEvery { repository.getHouse(houseUrl) } returns house
        coEvery { savedState.get<String>(Navigation.houseUrlParam) } returns houseUrl

        val vm = HouseDetailViewModel(repository, dispatcher, savedState)

        vm.state.test {
            assertTrue(awaitItem() is HouseDetailState.Loading)

            val successState = awaitItem()
            assertTrue(successState is HouseDetailState.Success)
            assertTrue((successState as HouseDetailState.Success).house == house)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Load house unsuccessfully and receive an error state`() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)

        coEvery { repository.getHouse(houseUrl) } returns null
        coEvery { savedState.get<String>(Navigation.houseUrlParam) } returns houseUrl

        val vm = HouseDetailViewModel(repository, dispatcher, savedState)

        vm.state.test {
            assertTrue(awaitItem() is HouseDetailState.Loading)

            assertTrue(awaitItem() is HouseDetailState.Error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Provide house url in savedState and it gets loaded into houseUrl member`() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)

        coEvery { repository.getHouse(houseUrl) } returns null
        coEvery { savedState.get<String>(Navigation.houseUrlParam) } returns houseUrl

        val vm = HouseDetailViewModel(repository, dispatcher, savedState)

        assertEquals(houseUrl, vm.houseUrl)
    }

    @Test
    fun `Provide no house url in savedState and houseUrl member is empty`() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)

        coEvery { repository.getHouse(houseUrl) } returns null
        coEvery { savedState.get<String>(Navigation.houseUrlParam) } returns null

        val vm = HouseDetailViewModel(repository, dispatcher, savedState)

        assertEquals("", vm.houseUrl)
    }
}