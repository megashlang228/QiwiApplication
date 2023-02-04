package com.example.qiwiapplication.network

import android.util.Log
import com.example.qiwiapplication.network.response.PaymentsResponse
import com.example.qiwiapplication.network.response.balanceresponse.BalanceResponse
import com.example.qiwiapplication.network.response.hookresponse.HookResponse
import com.example.qiwiapplication.network.response.rersoninforesponse.PersonInfoResponse
import retrofit2.Response

class ApiClient (
    private val qiwiService: QiwiService
    ) {

    suspend fun getPayments(wallet: String, token: String, rows: Int): SimpleResponse<PaymentsResponse> {
        return safeApiCall { qiwiService.getPayments(wallet, token, rows) }
    }

    suspend fun getPaymentsNextTxn(wallet: String, token: String, rows: Int, nextDate: String, nextId: Long): SimpleResponse<PaymentsResponse> {
        return safeApiCall { qiwiService.getPaymentsNextTxn(wallet, token, rows, nextDate, nextId) }
    }

    suspend fun getPersonInfo(wallet: String, token: String): SimpleResponse<PersonInfoResponse> {
        return safeApiCall { qiwiService.getPersonInfo(wallet, token) }
    }

    suspend fun getBalance(personId: String, token: String): SimpleResponse<BalanceResponse> {
        return safeApiCall { qiwiService.getBalance(personId, token) }
    }

    suspend fun subscribeHooks(personId: String, token: String): SimpleResponse<HookResponse> {
        return safeApiCall { qiwiService.subscribeHooks(personId, token) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            Log.e("error", e.toString())
            SimpleResponse.failure(e)
        }
    }
}