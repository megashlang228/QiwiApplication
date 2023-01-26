package com.example.qiwiapplication.model

data class Transactoin(
    val tnxId: Long,
    val date: String,
    val error: String?,
    val type: String,
    val status: String,
    val sum: Double,
    val currency: Int,
    val providerName: String,
    val providerIcon: String?
)