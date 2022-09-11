package io.redandroid.westerosandbeyond.repository

import io.redandroid.westerosandbeyond.core.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class HouseRepositoryTest {

    @Test
    fun addition_isCorrect() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)
        val repository = HouseRepositoryImpl(dispatcher)
    }
}