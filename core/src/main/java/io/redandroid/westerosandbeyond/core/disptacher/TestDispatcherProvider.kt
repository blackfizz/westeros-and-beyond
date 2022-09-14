package io.redandroid.westerosandbeyond.core.disptacher

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider(
    private val scheduler : TestCoroutineScheduler,
    private val dispatcher: TestDispatcher = StandardTestDispatcher(scheduler)
): DispatcherProvider {
    override val default get() = dispatcher
    override val io get() = dispatcher
    override val main get() = dispatcher
    override val unconfined get() = dispatcher
}