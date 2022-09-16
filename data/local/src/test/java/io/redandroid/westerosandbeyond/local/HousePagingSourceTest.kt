package io.redandroid.westerosandbeyond.local

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import io.redandroid.westerosandbeyond.core.disptacher.TestDispatcherProvider
import io.redandroid.westerosandbeyond.core.navigation.Navigation
import io.redandroid.westerosandbeyond.local.modules.house.dao.HouseDao
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HousePagingSourceTest {

    private lateinit var dao: HouseDao

    private val amountOfHouses = 100
    private val houses = run {
        val result = mutableListOf<House>()
        for (index in 0..amountOfHouses) {
            result.add(mockedHouse(index))
        }
        result
    }


    @Before
    fun setup() {
        dao = mockk()
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `Load house successfully and receive success state`() = runTest {

    }
}