package com.example.qiwiapplication.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiwiapplication.network.QiwiRepository
import com.example.qiwiapplication.network.response.rersoninforesponse.PersonInfoResponse
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val repository = QiwiRepository()

    private val _error = MutableLiveData<Unit>()
    var error: LiveData<Unit> = _error


    private val _personInfo = MutableLiveData<PersonInfoResponse>()
    var personInfo: LiveData<PersonInfoResponse> = _personInfo

    fun getPersonInfo(wallet: String, token: String) = viewModelScope.launch{
        val payments = repository.getPersonAuth(wallet, token)

        if(payments != null) {
            _personInfo.postValue(payments!!)
        }else{
            _error.postValue(Unit)
        }
    }


}