package com.example.qiwiapplication.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiwiapplication.network.QiwiRepository
import com.example.qiwiapplication.network.response.rersoninforesponse.PersonInfoResponse
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository = QiwiRepository()

    private val _personInfo = MutableLiveData<PersonInfoResponse>()
    val personInfo: LiveData<PersonInfoResponse> = _personInfo

    init {
        getPersonInfo()
    }

    fun getPersonInfo() = viewModelScope.launch{
        val request = repository.getPersonInfo()

        if(request != null){
            _personInfo.postValue(request!!)
        }
    }
}