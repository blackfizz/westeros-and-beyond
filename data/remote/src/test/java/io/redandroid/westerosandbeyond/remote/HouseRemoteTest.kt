package io.redandroid.westerosandbeyond.remote

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import io.redandroid.westerosandbeyond.core.disptacher.TestDispatcherProvider
import io.redandroid.westerosandbeyond.core.navigation.Navigation
import io.redandroid.westerosandbeyond.model.core.RemoteResult
import io.redandroid.westerosandbeyond.model.modules.house.PagedHouses
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import io.redandroid.westerosandbeyond.remote.modules.house.HouseApi
import io.redandroid.westerosandbeyond.remote.modules.house.HouseRemoteImpl
import io.redandroid.westerosandbeyond.remote.modules.house.model.mockedHouseDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HouseRemoteTest {

    private lateinit var api: HouseApi

    val housesDTO = listOf(mockedHouseDTO(1), mockedHouseDTO(2), mockedHouseDTO(3))
    val housesInternal = listOf(mockedHouse(1), mockedHouse(2), mockedHouse(3))

    private val house = PagedHouses(
        houses = housesInternal,
        nextUrl = "https://hereIsTheUrlToTheNextPage.com",
        previousUrl = "",
        firstUrl = "",
        lastUrl = "",
    )


    val linkHeader = mapOf("link" to "<${house.nextUrl}>; rel=\"next\"")
    val header = linkHeader.toHeaders()


    @Before
    fun setup() {
        api = mockk()
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `Load house successfully and receive success state`() = runTest {

        coEvery { api.fetchHouses(1) } returns Response.success(housesDTO, header)

        val remote = HouseRemoteImpl(api)
        val result = remote.fetchPagedHouses(1)

        assertTrue(result is RemoteResult.Success)
        result as RemoteResult.Success
        Assert.assertEquals(housesInternal, result.data.houses)
        Assert.assertEquals(house.nextUrl, result.data.nextUrl)
    }
}