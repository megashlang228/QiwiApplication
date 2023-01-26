package com.example.qiwiapplication.network.response.transactoin

data class Data(
    val account: String,
    val comment: String?,
    val commission: Commission,
    val currencyRate: Int,
    val date: String,
    val error: String?,
    val errorCode: Int,
    val features: Features,
    val paymentExtras: List<Any>,
    val personId: Long,
    val provider: Provider,
    val serviceExtras: ServiceExtras,
    val source: Source,
    val status: String,
    val statusText: String,
    val sum: Sum,
    val total: Total,
    val trmTxnId: String,
    val txnId: Long,
    val type: String,
    val view: View
)