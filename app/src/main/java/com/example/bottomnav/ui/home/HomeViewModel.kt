package com.example.bottomnav.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.network.MarsApi
import kotlinx.coroutines.launch
enum class MarsApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private val _status = MutableLiveData<MarsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MarsApiStatus> = _status

    private var _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

     fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
              //  _text.value = listResult
                _text.value = "Success: ${listResult.size} Mars photos retrieved"+"id of the first json is ${listResult.get(0).id}"

                //  _status.value = listResult
            }
            catch (e: Exception) {
              //  _status.value = "Failure: ${e.message}"

            }
        }
    }

}