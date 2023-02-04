package com.example.qiwiapplication.network

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.qiwiapplication.model.Transactoin
import com.example.qiwiapplication.network.paging.NextTxn
import com.example.qiwiapplication.network.paging.PaymentPageLoader
import com.example.qiwiapplication.network.paging.PaymentsPagingSource
import com.example.qiwiapplication.network.response.PaymentsResponse
import com.example.qiwiapplication.network.response.balanceresponse.BalanceResponse
import com.example.qiwiapplication.network.response.hookresponse.HookResponse
import com.example.qiwiapplication.network.response.rersoninforesponse.PersonInfoResponse
import com.example.qiwiapplication.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class QiwiRepository {

    suspend fun getPayments(rows: Int): PaymentsResponse? {
        val wallet = SharedPref.getPersonId()
        val token = SharedPref.getToken()

        val request = NetworkLayer.apiClient.getPayments(wallet,"Bearer " + token, rows)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getPaymentsNextTxn(rows: Int, nextTxn: NextTxn): PaymentsResponse? {
        val wallet = SharedPref.getPersonId()
        val token = SharedPref.getToken()

        val request = NetworkLayer.apiClient.getPaymentsNextTxn(wallet,"Bearer " + token, rows, nextTxn.nextTxnDate, nextTxn.nextTxnId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getPersonAuth(wallet: String, token: String): PersonInfoResponse? {
        val request = NetworkLayer.apiClient.getPersonInfo(wallet,"Bearer " + token)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        SharedPref.saveToken(token)
        SharedPref.savePersonId(wallet)

        return request.body
    }

    suspend fun getPersonInfo(): PersonInfoResponse? {
        val wallet = SharedPref.getPersonId()
        val token = SharedPref.getToken()

        val request = NetworkLayer.apiClient.getPersonInfo(wallet,"Bearer " + token)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun subscribeHooks(): HookResponse? {
        val wallet = SharedPref.getPersonId()
        val token = SharedPref.getToken()

        val request = NetworkLayer.apiClient.subscribeHooks(wallet,"Bearer " + token)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getBalance(): BalanceResponse? {
        if (!SharedPref.containsPersonId()) return null
        if (!SharedPref.containsToken()) return null

        val personId = SharedPref.getPersonId()
        val token = SharedPref.getToken()

        val request = NetworkLayer.apiClient.getBalance(personId, "Bearer " + token)

        if (request.failed || !request.isSuccessful) {
            Log.e("error", request.exception.toString())
            return null
        }

        return request.body
    }

    suspend fun hasAuth(): Boolean {
        with(SharedPref){containsPersonId() && containsToken()
            if(containsPersonId() && containsToken()){
                if(getPersonAuth(getPersonId(), getToken()) != null){
                    return true
                }
            }
            return false

        }
    }


    fun getPagedPayments(): Flow<PagingData<Transactoin>>{
        val loader: PaymentPageLoader = { nextTxn, pageSize ->
            println(nextTxn)
            println(pageSize)
            if(nextTxn == null) getPayments(pageSize)
            else getPaymentsNextTxn(pageSize, nextTxn)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {PaymentsPagingSource(loader)}
        ).flow
    }

    companion object{
        const val PAGE_SIZE = 5
    }
}