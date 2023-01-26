package com.example.qiwiapplication.network.paging

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.scan

    @ExperimentalCoroutinesApi
    fun <T> Flow<T>.simpleScan(count: Int): Flow<List<T?>> {
        val items = List<T?>(count) { null }
        return this.scan(items) { previous, value -> previous.drop(1) + value }
    }
