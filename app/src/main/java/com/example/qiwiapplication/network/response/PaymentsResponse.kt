package com.example.qiwiapplication.network.response

import com.example.qiwiapplication.network.response.transactoin.Data
import com.squareup.moshi.Json

data class PaymentsResponse(
    @Json(name="data")
    val transaction: List<Data>,
    val nextTxnDate: String?,
    val nextTxnId: Long?
)