package com.example.qiwiapplication.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiwiapplication.network.QiwiRepository
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val repository = QiwiRepository()

    private val _splashNavCommand = MutableLiveData<SplashNavCommand>()
    var splashNavCommand: LiveData<SplashNavCommand> = _splashNavCommand

    init {
        Log.e("init", "klsdlkjlsdkj")
        viewModelScope.launch {
            val auth = repository.hasAuth()
            if(auth){
                Log.e("navigate", "navigate to main")
                _splashNavCommand.postValue(SplashNavCommand.NAVIGATE_TO_MAIN)
            }else{
                Log.e("navigate", "navigate to auth")
                _splashNavCommand.postValue(SplashNavCommand.NAVIGATE_TO_AUTH)
            }
        }
    }


}

enum class SplashNavCommand {
    NAVIGATE_TO_MAIN,
    NAVIGATE_TO_AUTH
}