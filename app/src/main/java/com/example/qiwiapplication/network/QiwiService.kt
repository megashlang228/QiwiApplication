package com.example.qiwiapplication.network

import com.example.qiwiapplication.network.response.PaymentsResponse
import com.example.qiwiapplication.network.response.balanceresponse.BalanceResponse
import com.example.qiwiapplication.network.response.hookresponse.HookResponse
import com.example.qiwiapplication.network.response.rersoninforesponse.PersonInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface QiwiService {


    @GET("payment-history/v2/persons/{wallet}/payments?")
    suspend fun getPayments(@Path("wallet") wallet: String,
                            @Header("Authorization") token: String,
                            @Query("rows") rows: Int
    ): Response<PaymentsResponse>

    @PUT("payment-notifier/v1/hooks?hookType=1&param=https://d884-2a00-1fa1-b040-f36f-9508-7a38-9314-6c6f.eu.ngrok.io&txnType=2")
    suspend fun subscribeHooks(@Path("wallet") wallet: String,
                            @Header("Authorization") token: String
    ): Response<HookResponse>

    @GET("payment-history/v2/persons/{wallet}/payments?")
    suspend fun getPaymentsNextTxn(@Path("wallet") wallet: String,
                                @Header("Authorization") token: String,
                                @Query("rows") rows: Int,
                                @Query("nextTxnDate") nextDate: String,
                                @Query("nextTxnId") nextId: Long
    ): Response<PaymentsResponse>

    @GET("/identification/v1/persons/{wallet}/identification")
    suspend fun getPersonInfo(@Path("wallet") wallet: String,  @Header("Authorization") token: String): Response<PersonInfoResponse>

    @GET("/funding-sources/v2/persons/{personId}/accounts")
    suspend fun getBalance(@Path("personId") wallet: String,  @Header("Authorization") token: String): Response<BalanceResponse>


}