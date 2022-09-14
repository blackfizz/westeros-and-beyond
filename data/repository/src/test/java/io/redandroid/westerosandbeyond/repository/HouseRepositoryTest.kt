package io.redandroid.westerosandbeyond.repository

import io.redandroid.westerosandbeyond.core.disptacher.TestDispatcherProvider
import io.redandroid.westerosandbeyond.repository.modules.house.HouseRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HouseRepositoryTest {

    @Test
    fun addition_isCorrect() = runTest {
        val dispatcher = TestDispatcherProvider(testScheduler)
        val repository = HouseRepositoryImpl(dispatcher)
    }
}