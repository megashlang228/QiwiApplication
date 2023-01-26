package com.example.qiwiapplication.network.response.balanceresponse

data class Account(
    val alias: String,
    val balance: Balance?,
    val bankAlias: String,
    val currency: Int,
    val fsAlias: String,
    val hasBalance: Boolean,
    val title: String,
    val type: Type
)