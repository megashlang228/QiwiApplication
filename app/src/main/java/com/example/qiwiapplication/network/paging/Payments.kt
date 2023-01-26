package com.example.qiwiapplication.network.paging

import com.example.qiwiapplication.model.Transactoin

data class Payments(
    val transaction: List<Transactoin>,
    val nextTxnDate: String?,
    val nextTxnId: Long?
)