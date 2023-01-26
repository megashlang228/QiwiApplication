package com.example.qiwiapplication.network.mappers

import com.example.qiwiapplication.model.Transactoin
import com.example.qiwiapplication.network.paging.Payments
import com.example.qiwiapplication.network.response.PaymentsResponse
import com.example.qiwiapplication.network.response.transactoin.Data

object PaymentsMappers {

    fun buildFrom(response: PaymentsResponse?): Payments?{
        return if(response != null) {
            Payments(
                nextTxnDate = response.nextTxnDate,
                nextTxnId = response.nextTxnId,
                transaction = TransactionMappers.buildFrom(response.transaction)
            )
        }else null
    }
}

object TransactionMappers {

    fun buildFrom(response: List<Data>): List<Transactoin>{
        val list: MutableList<Transactoin> = mutableListOf()
        for(trans in response){
            list.add(
                Transactoin(
                tnxId = trans.txnId,
                date = trans.date,
                error = trans.error,
                type = trans.type,
                status = trans.status,
                sum = trans.sum.amount,
                currency = trans.sum.currency,
                providerName = trans.provider.shortName,
                providerIcon = trans.provider.logoUrl
            )
            )
        }
        return list
    }
}