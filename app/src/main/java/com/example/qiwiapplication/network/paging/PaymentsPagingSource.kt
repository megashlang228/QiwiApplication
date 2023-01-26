package com.example.qiwiapplication.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.qiwiapplication.model.Transactoin
import com.example.qiwiapplication.network.mappers.PaymentsMappers
import com.example.qiwiapplication.network.response.PaymentsResponse

typealias PaymentPageLoader = suspend (nextTxn: NextTxn?, pageSize: Int) -> PaymentsResponse?

class PaymentsPagingSource(
        private val loader: PaymentPageLoader
): PagingSource<NextTxn, Transactoin>() {


    override fun getRefreshKey(state: PagingState<NextTxn, Transactoin>): NextTxn? {
//        val anchorPosition = state.anchorPosition ?: return null
//        val page = state.closestPageToPosition(anchorPosition) ?: return null
//        return page.prevKey ?: page.nextKey
        return null
    }

    override suspend fun load(params: LoadParams<NextTxn>): LoadResult<NextTxn, Transactoin> {
        val nextTxn = params.key

        return try {
            val paymentsResponse = PaymentsMappers.buildFrom(loader.invoke(nextTxn, params.loadSize))

            return LoadResult.Page(
                data = paymentsResponse?.transaction ?: listOf(),

                prevKey = if(nextTxn == null) null
                        else{ if(paymentsResponse != null) {
                            NextTxn(
                                paymentsResponse.transaction.first().tnxId,
                                paymentsResponse.transaction.first().date
                            )
                        }
                else null
                            } ,

                nextKey = if(paymentsResponse != null) {
                    if(paymentsResponse.transaction.size == params.loadSize) {
                        if (paymentsResponse.nextTxnId != null && paymentsResponse.nextTxnDate != null) {
                                NextTxn(
                                    paymentsResponse.nextTxnId,
                                    paymentsResponse.nextTxnDate
                                )
                            }else null
                        }else null
                    }else null
                )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
}