package com.example.qiwiapplication.network.response.hookresponse

data class HookResponse(
    val dateTime: String,
    val description: String,
    val errorCode: String,
    val serviceName: String,
    val traceId: String,
    val userMessage: String
)