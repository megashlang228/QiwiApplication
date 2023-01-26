package com.example.qiwiapplication.ui.dashboard

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.qiwiapplication.model.Transactoin
import com.example.qiwiapplication.network.QiwiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class DashboardViewModel : ViewModel() {

    private val repository = QiwiRepository()
//    val transactionFlow = repository.getPagedPayments().cachedIn(viewModelScope)
////
    val transactionFlow: Flow<PagingData<Transactoin>>



    private val transactoin = MutableLiveData(Unit)

    init {
        transactionFlow = transactoin.asFlow()
            .debounce(500)
            .flatMapLatest {
                repository.getPagedPayments()
            }
            .cachedIn(viewModelScope)

//        transactionFlow = transactoin.asFlow()
//            .debounce(500)
//            .flatMapLatest {
//                repository.getPagedPayments()
//            }
//            .cachedIn(viewModelScope)
    }

    fun refresh() {
        transactoin.postValue(transactoin.value)
    }
}