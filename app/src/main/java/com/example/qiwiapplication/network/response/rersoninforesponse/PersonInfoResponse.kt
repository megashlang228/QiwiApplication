package com.example.qiwiapplication.network.response.rersoninforesponse

data class PersonInfoResponse(
    val birthDate: String,
    val firstName: String,
    val id: Long,
    val inn: String?,
    val lastName: String,
    val middleName: String,
    val oms: String?,
    val passport: String,
    val snils: String,
    val type: String
)