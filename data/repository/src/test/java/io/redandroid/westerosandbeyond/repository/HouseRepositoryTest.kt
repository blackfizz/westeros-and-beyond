package io.redandroid.westerosandbeyond.repository

import io.mockk.*
import io.redandroid.westerosandbeyond.core.disptacher.TestDispatcherProvider
import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import io.redandroid.westerosandbeyond.repository.contracts.local.HouseLocal
import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote
import io.redandroid.westerosandbeyond.repository.modules.house.HouseRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HouseRepositoryTest {

    private lateinit var remote: HouseRemote
    private lateinit var local: HouseLocal

    private val houses = listOf(
        mockedHouse(1),
        mockedHouse(2),
        mockedHouse(3),
        mockedHouse(4),
        mockedHouse(5),
    )

    private val pagedHouse = PagedHouses(houses, "", "", "", "")
    private val emptyPagedHouse = PagedHouses(listOf(), "", "", "", "")

    @Before
    fun setup() {
        remote = mockk()
        local = mockk()
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `fetch houses successfully and they get saved in the database`() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)

        coEvery { remote.fetchPagedHouses(1) } returns RemoteResult.Success(pagedHouse)
        coEvery { local.loadHouses() } returns houses
        coJustRun { local.insertHouses(any()) }

        val repository = HouseRepositoryImpl(remote, local, dispatcher)
        val receivedHouses = repository.getHouses()

        coVerify { local.insertHouses(houses) }
        assertTrue(houses == receivedHouses)
    }

    @Test
    fun `fetch zero houses and no insert is done in the local`() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)

        coEvery { remote.fetchPagedHouses(1) } returns RemoteResult.Success(emptyPagedHouse)
        coEvery { local.loadHouses() } returns listOf()
        coJustRun { local.insertHouses(any()) }

        val repository = HouseRepositoryImpl(remote, local, dispatcher)
        val receivedHouses = repository.getHouses()

        coVerify(exactly = 0) { local.insertHouses(any()) }
        assertTrue(receivedHouses.isEmpty())
    }
}