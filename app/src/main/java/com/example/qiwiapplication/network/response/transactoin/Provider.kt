package com.example.qiwiapplication.network.response.transactoin

data class Provider(
    val description: String?,
    val extras: List<Any>,
    val id: Int,
    val keys: String?,
    val logoUrl: String?,
    val longName: String?,
    val shortName: String,
    val siteUrl: String?
)