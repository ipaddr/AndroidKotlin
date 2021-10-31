package com.example.androidkotlin

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.example.androidkotlin.day4.paging.test.MyDiffCallback
import com.example.androidkotlin.day4.paging.test.NoopListCallback
import com.example.androidkotlin.day4.paging.test.myHelperTransformFunction
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PagingDataTransformTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun differTransformsData() = testScope.runBlockingTest {
        val data = PagingData.from(listOf(1, 2, 3, 4)).myHelperTransformFunction()
        val differ = AsyncPagingDataDiffer(
            diffCallback = MyDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )

        // You don't need to use lifecycleScope.launch() if you're using
        // PagingData.from()
        differ.submitData(data)

        // Wait for transforms and the differ to process all updates.
        advanceUntilIdle()
        assertEquals(listOf(4, 16), differ.snapshot().items)
    }
}