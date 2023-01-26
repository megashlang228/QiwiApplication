package com.example.qiwiapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiwiapplication.network.QiwiRepository
import com.example.qiwiapplication.network.response.PaymentsResponse
import com.example.qiwiapplication.network.response.balanceresponse.BalanceResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = QiwiRepository()

    private val _text = MutableLiveData<BalanceResponse>()
    var text: LiveData<BalanceResponse> = _text

    init {
        fetchPayments()
    }

    fun fetchPayments() = viewModelScope.launch {
        val payments = repository.getBalance()

        if(payments != null) {
            _text.postValue(payments!!)
        }
    }
}